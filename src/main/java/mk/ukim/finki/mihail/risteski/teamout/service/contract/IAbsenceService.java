package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.AbsenceCreateRequest;

import java.util.List;

public interface IAbsenceService
{
    List<AbsenceDto> GetAbsences(Long organizationId) throws NotFoundException;

    List<AbsenceDto> GetEmployeeAbsences(Long organizationId, Long employeeId) throws NotFoundException;

    List<AbsenceDto> GetAbsencesForApproval(Long organizationId, Long employeeId) throws NotFoundException;

    AbsenceDto GetAbsence(Long organizationId, Long absenceId) throws NotFoundException;

    void CreateAbsence(Long organizationId, Long employeeId, AbsenceCreateRequest request) throws NotFoundException;

    void DeleteAbsence(Long organizationId, Long employeeId, Long absenceId);

    void ApproveAbsence(Long organizationId, Long employeeId, Long absenceId);

    void RejectAbsence(Long organizationId, Long employeeId, Long absenceId);
}
