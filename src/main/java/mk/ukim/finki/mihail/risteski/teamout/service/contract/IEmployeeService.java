package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDetailsDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.EmployeeUpdateRequest;

import java.util.List;

public interface IEmployeeService
{
    List<EmployeeDto> GetEmployeeList(Long organizationId);

    List<EmployeeDto> InviteEmployee(Long organizationId, DraftUserCreateRequest draftUserCreateRequest);

    EmployeeDetailsDto GetEmployeeDetails(Long organizationId, Long employeeId);

    List<EmployeeDto> GetEmployeeNotResponsible(Long organizationId, Long employeeId);

    void AddEmployeeResponsibleTo(Long organizationId, Long employeeId, Long[] employeeIds);

    void AddEmployeeResponsibleFor(Long organizationId, Long employeeId, Long[] employeeIds);

    void UpdateEmployeeDetails(Long organizationId, Long employeeId, EmployeeUpdateRequest employeeUpdateRequest);

    void RemoveEmployeeResponsibleTo(Long organizationId, Long employeeId, Long responsibleToEmployeeId);

    void RemoveEmployeeResponsibleFor(Long organizationId, Long employeeId, Long responsibleForEmployeeId);
}
