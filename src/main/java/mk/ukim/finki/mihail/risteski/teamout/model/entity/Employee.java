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

    @OneToOne
    private UserInOrganization UserInOrganization;

    @ManyToMany
    private List<Employee> ResponsibleFor;

    @ManyToMany(mappedBy = "ResponsibleFor")
    private List<Employee> ResponsibleTo;

    @OneToMany
    private List<Absence> Absences;
}
