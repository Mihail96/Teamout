package mk.ukim.finki.mihail.risteski.teamout.model.dto;

public class AbsenceDto
{
    private String DateFrom;

    private String DateTo;

    private String AbsenceStatus;

    private String AbsenceType;

    private String EmployeeFirstName;

    private String EmployeeLastName;

    private Long Id;

    private Long EmployeeId;

    public String getDateFrom()
    {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom)
    {
        DateFrom = dateFrom;
    }

    public String getDateTo()
    {
        return DateTo;
    }

    public void setDateTo(String dateTo)
    {
        DateTo = dateTo;
    }

    public String getAbsenceStatus()
    {
        return AbsenceStatus;
    }

    public void setAbsenceStatus(String absenceStatusEnum)
    {
        AbsenceStatus = absenceStatusEnum;
    }

    public String getAbsenceType()
    {
        return AbsenceType;
    }

    public void setAbsenceType(String absenceTypeEnum)
    {
        AbsenceType = absenceTypeEnum;
    }

    public String getEmployeeFirstName()
    {
        return EmployeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName)
    {
        EmployeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName()
    {
        return EmployeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName)
    {
        EmployeeLastName = employeeLastName;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public Long getEmployeeId()
    {
        return EmployeeId;
    }

    public void setEmployeeId(Long employeeId)
    {
        EmployeeId = employeeId;
    }
}
