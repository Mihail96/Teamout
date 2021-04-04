package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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
}
