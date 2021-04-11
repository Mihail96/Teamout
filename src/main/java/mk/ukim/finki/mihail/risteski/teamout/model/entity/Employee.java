package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(columnDefinition = "int4 not null default 0")
    private int HolidayDaysUsed;

    @Column(columnDefinition = "int4 not null default 0")
    private int HolidayDaysBalance;

    @Column(columnDefinition = "int4 not null default 0")
    private int SickleaveDaysUsed;

    @Column(columnDefinition = "int4 not null default 0")
    private int SickleaveDaysBalance;

    @Column(columnDefinition = "int4 not null default 0")
    private int ExtraordinaryDaysUsed;

    @Column(columnDefinition = "int4 not null default 0")
    private int ExtraordinaryDaysBalance;

    @OneToOne(mappedBy = "Employee")
    private UserInOrganization UserInOrganization;

    @ManyToMany
    private List<Employee> ResponsibleFor;

    @ManyToMany(mappedBy = "ResponsibleFor")
    private List<Employee> ResponsibleTo;

    @OneToMany(mappedBy = "Employee")
    private List<Absence> Absences;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.UserInOrganization getUserInOrganization()
    {
        return UserInOrganization;
    }

    public void setUserInOrganization(mk.ukim.finki.mihail.risteski.teamout.model.entity.UserInOrganization userInOrganization)
    {
        UserInOrganization = userInOrganization;
    }

    public List<Employee> getResponsibleFor()
    {
        return ResponsibleFor;
    }

    public void setResponsibleFor(List<Employee> responsibleFor)
    {
        ResponsibleFor = responsibleFor;
    }

    public List<Employee> getResponsibleTo()
    {
        return ResponsibleTo;
    }

    public void setResponsibleTo(List<Employee> responsibleTo)
    {
        ResponsibleTo = responsibleTo;
    }

    public List<Absence> getAbsences()
    {
        return Absences;
    }

    public void setAbsences(List<Absence> absences)
    {
        Absences = absences;
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
