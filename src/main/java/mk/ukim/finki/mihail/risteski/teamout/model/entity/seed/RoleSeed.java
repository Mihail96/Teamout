package mk.ukim.finki.mihail.risteski.teamout.model.entity.seed;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.Role;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

public class RoleSeed
{
    public static void SeedRoles(RoleRepository roleRepository)
    {
        List<Role> roles = roleRepository.findAll();
        List<RoleEnum> RoleEnums = RoleEnum.GetAll();

        for (RoleEnum roleEnum: RoleEnums)
        {
            Optional<Role> role =
                    roles.stream().filter(x -> x.getId().equals(roleEnum.getId())).findFirst();

            if(role.isEmpty())
            {
                Role roleToAdd = new Role();
                roleToAdd.setId(roleEnum.getId());
                roleToAdd.setName(roleEnum.getName());
                roleRepository.save(roleToAdd);
            }
        }

        roleRepository.flush();
    }
}
