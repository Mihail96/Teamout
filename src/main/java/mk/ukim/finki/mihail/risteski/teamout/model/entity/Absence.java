package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Absence
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Date DateFrom;

    private Date DateTo;

    @ManyToOne
    private Employee Employee;

    @ManyToOne
    private AbsenceType Type;

    @ManyToOne
    private AbsenceStatus Status;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public Date getDateFrom()
    {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom)
    {
        DateFrom = dateFrom;
    }

    public Date getDateTo()
    {
        return DateTo;
    }

    public void setDateTo(Date dateTo)
    {
        DateTo = dateTo;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee getEmployee()
    {
        return Employee;
    }

    public void setEmployee(mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee employee)
    {
        Employee = employee;
    }

    public AbsenceType getType()
    {
        return Type;
    }

    public void setType(AbsenceType type)
    {
        Type = type;
    }

    public AbsenceStatus getStatus()
    {
        return Status;
    }

    public void setStatus(AbsenceStatus status)
    {
        Status = status;
    }
}
