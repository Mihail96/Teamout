package mk.ukim.finki.mihail.risteski.teamout.service;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceStatusEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceTypeEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.AbsenceCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.EmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAbsenceService;
import mk.ukim.finki.mihail.risteski.teamout.util.AbsenceUtil;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AbsenceService implements IAbsenceService
{
    private final AbsenceRepository _absenceRepository;
    private final EmployeeRepository _employeeRepository;

    public AbsenceService(AbsenceRepository absenceRepository,
                          EmployeeRepository employeeRepository)
    {
        _absenceRepository = absenceRepository;
        _employeeRepository = employeeRepository;
    }

    @Override
    public List<AbsenceDto> GetAbsences(Long organizationId) throws NotFoundException
    {
        List<Absence> absences = _absenceRepository.GetAbsencesInOrganization(organizationId);
        List<AbsenceDto> absenceDtos = new ArrayList<>();

        for (Absence absence: absences)
        {
            absenceDtos.add(AbsenceUtil.CreateAbsenceDto(absence));
        }

        return absenceDtos;
    }

    @Override
    public List<AbsenceDto> GetEmployeeAbsences(Long organizationId, Long employeeId) throws NotFoundException
    {
        List<Absence> absences = _absenceRepository.GetAbsencesForEmployee(organizationId, employeeId);
        List<AbsenceDto> absenceDtos = new ArrayList<>();

        for (Absence absence: absences)
        {
            absenceDtos.add(AbsenceUtil.CreateAbsenceDto(absence));
        }

        return absenceDtos;
    }

    @Override
    public List<AbsenceDto> GetAbsencesForApproval(Long organizationId, Long employeeId) throws NotFoundException
    {
        Employee employee = _employeeRepository.GetEmployeeById(employeeId);
        List<Absence> absences = employee.getResponsibleFor()
                                         .stream()
                                         .map(Employee::getAbsences)
                                         .flatMap(List::stream)
                                         .collect(Collectors.toList());

        List<AbsenceDto> absenceDtos = new ArrayList<>();

        for (Absence absence: absences)
        {
            absenceDtos.add(AbsenceUtil.CreateAbsenceDto(absence));
        }

        return absenceDtos;
    }

    @Override
    public AbsenceDto GetAbsence(Long organizationId, Long absenceId) throws NotFoundException
    {
        Absence absence = _absenceRepository.GetAbsence(organizationId, absenceId);

        return AbsenceUtil.CreateAbsenceDto(absence);
    }

    @Override
    public void CreateAbsence(Long organizationId,
                              Long employeeId,
                              AbsenceCreateRequest request) throws NotFoundException, ValidationException
    {
        Employee employee = _employeeRepository.GetEmployeeById(employeeId);

        if(request.getDateFrom().after(request.getDateTo())) {
            throw new ValidationException("Invalid dates");
        }

        long diffInMillies = Math.abs(request.getDateFrom().getTime() - request.getDateTo().getTime());
        long requestedDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        AbsenceTypeEnum absenceTypeEnum = AbsenceTypeEnum.GetByName(AbsenceTypeEnum.None, request.getAbsenceType());

        ValidateDays(employee, absenceTypeEnum, requestedDays);

        Absence absence = new Absence();
        absence.setDateFrom(request.getDateFrom());
        absence.setDateTo(request.getDateTo());
        absence.setStatus(AbsenceStatusEnum.Pending.ToAbsenceStatus());
        absence.setType(absenceTypeEnum.ToAbsenceType());
        absence.setEmployee(employee);

        _absenceRepository.saveAndFlush(absence);
    }

    @Override
    public void DeleteAbsence(Long organizationId, Long employeeId, Long absenceId)
    {
        Absence absence = _absenceRepository.GetAbsenceById(absenceId);

        _absenceRepository.delete(absence);
        _absenceRepository.flush();
    }

    @Override
    public void ApproveAbsence(Long organizationId, Long employeeId, Long absenceId) throws NotFoundException, ValidationException
    {
        Absence absence = _absenceRepository.GetAbsenceById(absenceId);
        absence.setStatus(AbsenceStatusEnum.Approved.ToAbsenceStatus());

        Employee employee = _employeeRepository.GetEmployeeById(absence.getEmployee().getId());

        AbsenceTypeEnum absenceTypeEnum = AbsenceTypeEnum.GetByName(AbsenceTypeEnum.None, absence.getType().getName());

        long diffInMillies = Math.abs(absence.getDateFrom().getTime() - absence.getDateTo().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        ValidateDays(employee, absenceTypeEnum, days);

        if (AbsenceTypeEnum.Holiday.equals(absenceTypeEnum))
        {
            employee.setHolidayDaysUsed((int) (employee.getHolidayDaysUsed() + days));
            employee.setHolidayDaysBalance((int) (employee.getHolidayDaysBalance() - days));
        }

        if (AbsenceTypeEnum.Sickleave.equals(absenceTypeEnum))
        {
            employee.setSickleaveDaysUsed((int) (employee.getSickleaveDaysUsed() + days));
            employee.setSickleaveDaysBalance((int) (employee.getSickleaveDaysBalance() - days));
        }

        if (AbsenceTypeEnum.ExtraOrdinary.equals(absenceTypeEnum))
        {
            employee.setExtraordinaryDaysUsed((int) (employee.getExtraordinaryDaysUsed() + days));
            employee.setExtraordinaryDaysBalance((int) (employee.getExtraordinaryDaysBalance() - days));
        }

        _employeeRepository.save(employee);
        _absenceRepository.saveAndFlush(absence);
    }

    private void ValidateDays(Employee employee, AbsenceTypeEnum absenceTypeEnum, long days) throws ValidationException
    {
        if (AbsenceTypeEnum.Holiday.equals(absenceTypeEnum) &&
                employee.getHolidayDaysBalance() < days)
        {
            throw new ValidationException("Not enough days");
        }

        if (AbsenceTypeEnum.Sickleave.equals(absenceTypeEnum) &&
                employee.getSickleaveDaysBalance() < days)
        {
            throw new ValidationException("Not enough days");
        }

        if (AbsenceTypeEnum.ExtraOrdinary.equals(absenceTypeEnum) &&
                employee.getExtraordinaryDaysBalance() < days)
        {
            throw new ValidationException("Not enough days");
        }
    }

    @Override
    public void RejectAbsence(Long organizationId, Long employeeId, Long absenceId)
    {
        Absence absence = _absenceRepository.GetAbsenceById(absenceId);
        absence.setStatus(AbsenceStatusEnum.Rejected.ToAbsenceStatus());
        _absenceRepository.saveAndFlush(absence);
    }
}
