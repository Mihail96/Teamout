package mk.ukim.finki.mihail.risteski.teamout.model.auth;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;

import java.util.Set;

public class CurrentUser
{
    private Long OrganizationId;

    private Long LogoId;

    private String OrganizationName;

    private Long UserId;

    private Set<String> Roles;

    private String FirstName;

    private String LastName;

    private String Email;

    private EmployeeUser EmployeeUser;

    public Long getOrganizationId()
    {
        return OrganizationId;
    }

    public void setOrganizationId(Long organizationId)
    {
        OrganizationId = organizationId;
    }

    public Long getUserId()
    {
        return UserId;
    }

    public void setUserId(Long userId)
    {
        UserId = userId;
    }

    public Set<String> getRoles()
    {
        return Roles;
    }

    public void setRoles(Set<String> roles)
    {
        Roles = roles;
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

    public Long getLogoId()
    {
        return LogoId;
    }

    public void setLogoId(Long logoId)
    {
        LogoId = logoId;
    }

    public String getOrganizationName()
    {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        OrganizationName = organizationName;
    }

    public EmployeeUser getEmployeeUser()
    {
        return EmployeeUser;
    }

    public void setEmployeeUser(EmployeeUser employeeUser)
    {
        this.EmployeeUser = employeeUser;
    }
}
