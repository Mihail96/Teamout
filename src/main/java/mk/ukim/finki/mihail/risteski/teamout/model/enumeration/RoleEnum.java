package mk.ukim.finki.mihail.risteski.teamout.model.enumeration;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class RoleEnum extends Enumeration implements GrantedAuthority
{
    public static final RoleEnum None = new RoleEnum(-1L, "ROLE_NONE");
    public static final RoleEnum Owner = new RoleEnum(0L, "ROLE_OWNER");
    public static final RoleEnum Admin = new RoleEnum(1L, "ROLE_ADMIN");
    public static final RoleEnum Employee = new RoleEnum(2L, "ROLE_EMPLOYEE");

    private RoleEnum(Long id, String name){
        super(id, name);
    }

    public static List<RoleEnum> GetAll()
    {
        return All(None);
    }

    public static Role ToRole(RoleEnum roleEnum){
        Role role = new Role();

        role.setId(roleEnum.getId());
        role.setName(roleEnum.getName());

        return role;
    }

    @Override
    public String getAuthority()
    {
        return getName();
    }
}
