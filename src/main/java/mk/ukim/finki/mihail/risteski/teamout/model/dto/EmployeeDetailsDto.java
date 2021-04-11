package mk.ukim.finki.mihail.risteski.teamout.model.dto;

import javax.persistence.Column;
import java.util.List;

public class EmployeeDetailsDto extends EmployeeDto
{
    private List<EmployeeDto> ResponsibleToEmployee;
    private List<EmployeeDto> ResponsibleForEmployees;

    public List<EmployeeDto> getResponsibleToEmployee()
    {
        return ResponsibleToEmployee;
    }

    public void setResponsibleToEmployee(List<EmployeeDto> responsibleToEmployee)
    {
        ResponsibleToEmployee = responsibleToEmployee;
    }

    public List<EmployeeDto> getResponsibleForEmployees()
    {
        return ResponsibleForEmployees;
    }

    public void setResponsibleForEmployees(List<EmployeeDto> responsibleForEmployees)
    {
        ResponsibleForEmployees = responsibleForEmployees;
    }
}
