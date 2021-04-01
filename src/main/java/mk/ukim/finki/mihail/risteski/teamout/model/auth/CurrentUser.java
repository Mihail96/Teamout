package mk.ukim.finki.mihail.risteski.teamout.model.auth;

import java.util.Set;

public class CurrentUser
{
    private Long UserOrganizationId;

    private Long UserId;

    private Set<String> Roles;

    private String FirstName;

    private String LastName;

    private String Email;

    public Long getUserOrganizationId()
    {
        return UserOrganizationId;
    }

    public void setUserOrganizationId(Long userOrganizationId)
    {
        UserOrganizationId = userOrganizationId;
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
}
