package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
public class DraftUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String ActivationCode;

    private String Email;

    private String FirstName;

    private String LastName;

    @ManyToOne
    private Role Role;

    @ManyToOne
    private Organization Organization;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getActivationCode()
    {
        return ActivationCode;
    }

    public void setActivationCode(String activationCode)
    {
        ActivationCode = activationCode;
    }

    public Role getRole()
    {
        return Role;
    }

    public void setRole(Role role)
    {
        Role = role;
    }

    public Organization getOrganization()
    {
        return Organization;
    }

    public void setOrganization(Organization organization)
    {
        Organization = organization;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }
}
