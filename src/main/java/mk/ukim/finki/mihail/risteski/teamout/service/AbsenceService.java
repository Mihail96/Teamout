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
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
            absenceDtos.add(CreateAbsenceDto(absence));
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
            absenceDtos.add(CreateAbsenceDto(absence));
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
            absenceDtos.add(CreateAbsenceDto(absence));
        }

        return absenceDtos;
    }

    @Override
    public AbsenceDto GetAbsence(Long organizationId, Long absenceId) throws NotFoundException
    {
        Absence absence = _absenceRepository.GetAbsence(organizationId, absenceId);

        return CreateAbsenceDto(absence);
    }

    @Override
    public void CreateAbsence(Long organizationId,
                              Long employeeId,
                              AbsenceCreateRequest request) throws NotFoundException
    {
        Absence absence = new Absence();
        absence.setDateFrom(request.getDateFrom());
        absence.setDateTo(request.getDateTo());
        absence.setStatus(AbsenceStatusEnum.Pending.ToAbsenceStatus());
        AbsenceTypeEnum absenceTypeEnum = AbsenceTypeEnum.GetByName(AbsenceTypeEnum.None, request.getAbsenceType());
        absence.setType(absenceTypeEnum.ToAbsenceType());

        Employee employee = _employeeRepository.GetEmployeeById(employeeId);
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
    public void ApproveAbsence(Long organizationId, Long employeeId, Long absenceId)
    {
        Absence absence = _absenceRepository.GetAbsenceById(absenceId);
        absence.setStatus(AbsenceStatusEnum.Approved.ToAbsenceStatus());
        _absenceRepository.saveAndFlush(absence);
    }

    @Override
    public void RejectAbsence(Long organizationId, Long employeeId, Long absenceId)
    {
        Absence absence = _absenceRepository.GetAbsenceById(absenceId);
        absence.setStatus(AbsenceStatusEnum.Rejected.ToAbsenceStatus());
        _absenceRepository.saveAndFlush(absence);
    }

    private AbsenceDto CreateAbsenceDto(Absence absence) throws NotFoundException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        AbsenceDto absenceDto = new AbsenceDto();
        absenceDto.setId(absence.getId());
        absenceDto.setDateFrom(dateFormat.format(absence.getDateFrom()));
        absenceDto.setDateTo(dateFormat.format(absence.getDateTo()));
        absenceDto.setAbsenceStatus(absence.getStatus().getName());
        absenceDto.setAbsenceType(absence.getType().getName());

        Employee employee = absence.getEmployee();
        User user = employee.getUserInOrganization().getUser();
        absenceDto.setEmployeeId(employee.getId());
        absenceDto.setEmployeeFirstName(user.getFirstName());
        absenceDto.setEmployeeLastName(user.getLastName());

        return absenceDto;
    }
}
