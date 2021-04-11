package mk.ukim.finki.mihail.risteski.teamout.util;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDetailsDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtils
{
    public static EmployeeDto CreateEmployeeDto(Employee employee)
    {
        EmployeeDto employeeDto = new EmployeeDto();
        User user = employee.getUserInOrganization().getUser();

        employeeDto.setUser(UserUtils.CreateUserDto(user));
        employeeDto.setId(employee.getId());

        return employeeDto;
    }
}
