package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;

import java.util.List;

public interface IAbsenceService
{
    List<AbsenceDto> GetAbsences(Long organizationId) throws NotFoundException;

    List<AbsenceDto> GetUserAbsences(Long organizationId, Long employeeId);

    AbsenceDto GetAbsence(Long organizationId, Long absenceId);
}
