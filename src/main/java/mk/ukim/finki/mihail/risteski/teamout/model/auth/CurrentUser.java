package mk.ukim.finki.mihail.risteski.teamout.model.auth;

import java.util.Set;

public class CurrentUser
{
    private Long UserOrganizationId;

    private Long UserId;

    private Set<String> Roles;

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
}
