package mk.ukim.finki.mihail.risteski.teamout.model.dto;

import javax.persistence.Column;
import java.util.List;

public class EmployeeDetailsDto extends EmployeeDto
{
    private int HolidayDaysUsed;
    private int HolidayDaysBalance;
    private int SickleaveDaysUsed;
    private int SickleaveDaysBalance;
    private int ExtraordinaryDaysUsed;
    private int ExtraordinaryDaysBalance;

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

    public int getHolidayDaysUsed()
    {
        return HolidayDaysUsed;
    }

    public void setHolidayDaysUsed(int holidayDaysUsed)
    {
        HolidayDaysUsed = holidayDaysUsed;
    }

    public int getHolidayDaysBalance()
    {
        return HolidayDaysBalance;
    }

    public void setHolidayDaysBalance(int holidayDaysBalance)
    {
        HolidayDaysBalance = holidayDaysBalance;
    }

    public int getSickleaveDaysUsed()
    {
        return SickleaveDaysUsed;
    }

    public void setSickleaveDaysUsed(int sickleaveDaysUsed)
    {
        SickleaveDaysUsed = sickleaveDaysUsed;
    }

    public int getSickleaveDaysBalance()
    {
        return SickleaveDaysBalance;
    }

    public void setSickleaveDaysBalance(int sickleaveDaysBalance)
    {
        SickleaveDaysBalance = sickleaveDaysBalance;
    }

    public int getExtraordinaryDaysUsed()
    {
        return ExtraordinaryDaysUsed;
    }

    public void setExtraordinaryDaysUsed(int extraordinaryDaysUsed)
    {
        ExtraordinaryDaysUsed = extraordinaryDaysUsed;
    }

    public int getExtraordinaryDaysBalance()
    {
        return ExtraordinaryDaysBalance;
    }

    public void setExtraordinaryDaysBalance(int extraordinaryDaysBalance)
    {
        ExtraordinaryDaysBalance = extraordinaryDaysBalance;
    }
}
