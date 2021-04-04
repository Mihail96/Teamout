package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserInOrganization
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private Role Role;

    @ManyToOne(fetch = FetchType.EAGER)
    private User User;

    @ManyToOne(fetch = FetchType.EAGER)
    private Organization Organization;

    @OneToOne
    private Employee Employee;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.Role getRole()
    {
        return Role;
    }

    public void setRole(mk.ukim.finki.mihail.risteski.teamout.model.entity.Role role)
    {
        Role = role;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.User getUser()
    {
        return User;
    }

    public void setUser(mk.ukim.finki.mihail.risteski.teamout.model.entity.User user)
    {
        User = user;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization getOrganization()
    {
        return Organization;
    }

    public void setOrganization(mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization organization)
    {
        Organization = organization;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee getEmployee()
    {
        return Employee;
    }

    public void setEmployee(mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee employee)
    {
        Employee = employee;
    }
}
