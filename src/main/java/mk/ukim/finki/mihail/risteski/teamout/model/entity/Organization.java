package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Organization
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 50)
    private String Name;

    @OneToOne
    private Image Logo;

    @OneToOne
    private Address Address;

    @OneToMany(mappedBy = "Organization", fetch = FetchType.LAZY)
    private List<UserInOrganization> UsersInOrganization;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public Image getLogo()
    {
        return Logo;
    }

    public void setLogo(Image logo)
    {
        Logo = logo;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.Address getAddress()
    {
        return Address;
    }

    public void setAddress(mk.ukim.finki.mihail.risteski.teamout.model.entity.Address address)
    {
        Address = address;
    }

    public List<UserInOrganization> getUsersInOrganization()
    {
        return UsersInOrganization;
    }

    public void setUsersInOrganization(List<UserInOrganization> usersInOrganization)
    {
        UsersInOrganization = usersInOrganization;
    }
}
