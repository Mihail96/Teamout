package mk.ukim.finki.mihail.risteski.teamout.eventListener;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.seed.AbsenceStatusSeed;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.seed.AbsenceTypeSeed;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.seed.RoleSeed;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceStatusRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceTypeRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.RoleRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedEventListener
{
    private final AbsenceTypeRepository _absenceTypeRepository;
    private final AbsenceStatusRepository _absenceStatusRepository;
    private final RoleRepository _roleRepository;

    public SeedEventListener(AbsenceTypeRepository absenceTypeRepository,
                             AbsenceStatusRepository absenceStatusRepository,
                             RoleRepository roleRepository)
    {
        _absenceTypeRepository = absenceTypeRepository;
        _absenceStatusRepository = absenceStatusRepository;
        _roleRepository = roleRepository;
    }

    @EventListener
    public void Seed(ContextRefreshedEvent event)
    {
        AbsenceTypeSeed.SeedAbsenceTypes(_absenceTypeRepository);
        AbsenceStatusSeed.SeedAbsenceStatus(_absenceStatusRepository);
        RoleSeed.SeedRoles(_roleRepository);
    }
}
