package mk.ukim.finki.mihail.risteski.teamout.service;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceStatusEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceTypeEnum;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAbsenceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbsenceService implements IAbsenceService
{
    private final AbsenceRepository _absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository)
    {
        _absenceRepository = absenceRepository;
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
    public List<AbsenceDto> GetUserAbsences(Long organizationId, Long employeeId)
    {
        List<Absence> absences = _absenceRepository.GetAbsencesForEmployee(organizationId);
        List<AbsenceDto> absenceDtos = new ArrayList<>();

        for (Absence absence: absences)
        {
            absenceDtos.add(CreateAbsenceDto(absence));
        }

        return absenceDtos;
    }

    @Override
    public AbsenceDto GetAbsence(Long organizationId, Long absenceId)
    {
        return null;
    }

    private AbsenceDto CreateAbsenceDto(Absence absence) throws NotFoundException
    {
        AbsenceDto absenceDto = new AbsenceDto();
        absenceDto.setDateFrom(absence.getDateFrom());
        absenceDto.setDateTo(absence.getDateTo());
        absenceDto.setAbsenceStatusEnum(AbsenceStatusEnum.GetById(AbsenceStatusEnum.None, absence.getStatus().getId()));
        absenceDto.setAbsenceTypeEnum(AbsenceTypeEnum.GetById(AbsenceTypeEnum.None, absence.getType().getId()));

        return absenceDto;
    }
}
