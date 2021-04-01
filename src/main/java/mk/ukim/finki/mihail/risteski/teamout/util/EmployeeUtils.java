package mk.ukim.finki.mihail.risteski.teamout.util;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;

public class EmployeeUtils
{
    public static EmployeeDto CreateEmployeeDto(Employee employee)
    {
        EmployeeDto employeeDto = new EmployeeDto();
        User user = employee.getUserInOrganization().getUser();

        employeeDto.setUser(UserUtils.CreateUserDto(user));

        return employeeDto;
    }
}
