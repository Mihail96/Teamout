package mk.ukim.finki.mihail.risteski.teamout.model.entity;

import javassist.NotFoundException;
import lombok.Data;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 50)
    private String FirstName;

    @Column(length = 50)
    private String LastName;

    @Column(length = 320)
    private String Email;

    private String PasswordSalt;

    private String PasswordHash;

    private String PhoneNumber;

    @OneToOne
    private Address Address;

    @OneToOne(fetch = FetchType.LAZY)
    private Image Picture;

    @OneToMany(mappedBy = "User", fetch = FetchType.EAGER)
    private List<UserInOrganization> UserInOrganizations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return UserInOrganizations.stream()
                .map(x ->
                {
                    try
                    {
                        return RoleEnum.GetByName(RoleEnum.None, x.getRole().getName());
                    }
                    catch (NotFoundException e)
                    {
                        return RoleEnum.None;
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return PasswordHash;
    }

    @Override
    public String getUsername()
    {
        return Email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
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

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getPasswordSalt()
    {
        return PasswordSalt;
    }

    public void setPasswordSalt(String passwordSalt)
    {
        PasswordSalt = passwordSalt;
    }

    public String getPasswordHash()
    {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        PasswordHash = passwordHash;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        PhoneNumber = phoneNumber;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.entity.Address getAddress()
    {
        return Address;
    }

    public void setAddress(mk.ukim.finki.mihail.risteski.teamout.model.entity.Address address)
    {
        Address = address;
    }

    public Image getPicture()
    {
        return Picture;
    }

    public void setPicture(Image picture)
    {
        Picture = picture;
    }

    public List<UserInOrganization> getUserInOrganizations()
    {
        return UserInOrganizations;
    }

    public void setUserInOrganizations(List<UserInOrganization> userInOrganizations)
    {
        UserInOrganizations = userInOrganizations;
    }
}
