package mk.ukim.finki.mihail.risteski.teamout.util;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDetailsDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil
{
    public static EmployeeDto CreateEmployeeDto(Employee employee)
    {
        EmployeeDto employeeDto = new EmployeeDto();
        User user = employee.getUserInOrganization().getUser();

        employeeDto.setUser(UserUtil.CreateUserDto(user));
        employeeDto.setId(employee.getId());

        return employeeDto;
    }

    public static EmployeeDetailsDto CreateEmployeeDetailsDto(Employee employee)
    {
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();

        User user = employee.getUserInOrganization().getUser();
        employeeDetailsDto.setUser(UserUtil.CreateUserDto(user));
        employeeDetailsDto.setId(employee.getId());

        employeeDetailsDto.setHolidayDaysBalance(employee.getHolidayDaysBalance());
        employeeDetailsDto.setHolidayDaysUsed(employee.getHolidayDaysUsed());
        employeeDetailsDto.setSickleaveDaysBalance(employee.getSickleaveDaysBalance());
        employeeDetailsDto.setSickleaveDaysUsed(employee.getSickleaveDaysUsed());
        employeeDetailsDto.setExtraordinaryDaysBalance(employee.getExtraordinaryDaysBalance());
        employeeDetailsDto.setExtraordinaryDaysUsed(employee.getExtraordinaryDaysUsed());

        List<EmployeeDto> responsibleToEmployee = new ArrayList<>();
        for (Employee currentEmployee: employee.getResponsibleTo())
        {
            responsibleToEmployee.add(EmployeeUtil.CreateEmployeeDto(currentEmployee));
        }
        employeeDetailsDto.setResponsibleToEmployee(responsibleToEmployee);

        List<EmployeeDto> responsibleForEmployees = new ArrayList<>();
        for (Employee currentEmployee: employee.getResponsibleFor())
        {
            responsibleForEmployees.add(EmployeeUtil.CreateEmployeeDto(currentEmployee));
        }
        employeeDetailsDto.setResponsibleForEmployees(responsibleForEmployees);
        return employeeDetailsDto;
    }
}
