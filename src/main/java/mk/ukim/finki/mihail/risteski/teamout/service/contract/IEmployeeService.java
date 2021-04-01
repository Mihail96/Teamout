package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.EmployeeCreateRequest;

import java.util.List;

public interface IEmployeeService
{
    List<EmployeeDto> GetEmployeeList(Long organizationId);


    List<EmployeeDto> InviteEmployee(Long organizationId, EmployeeCreateRequest employeeCreateRequest);
}
