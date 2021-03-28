package mk.ukim.finki.mihail.risteski.teamout.model.entity.seed;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.AbsenceStatus;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceStatusEnum;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceStatusRepository;

import java.util.List;
import java.util.Optional;

public class AbsenceStatusSeed
{
    public static void SeedAbsenceStatus(AbsenceStatusRepository absenceStatusRepository)
    {
        List<AbsenceStatus> absenceStatuses = absenceStatusRepository.findAll();
        List<AbsenceStatusEnum> absenceStatusEnums = AbsenceStatusEnum.GetAll();

        for (AbsenceStatusEnum absenceStatusEnum: absenceStatusEnums)
        {
            Optional<AbsenceStatus> absenceType =
                    absenceStatuses.stream().filter(x -> x.getId().equals(absenceStatusEnum.getId())).findFirst();

            if(absenceType.isEmpty())
            {
                AbsenceStatus absenceStatusToAdd = new AbsenceStatus();
                absenceStatusToAdd.setId(absenceStatusEnum.getId());
                absenceStatusToAdd.setName(absenceStatusEnum.getName());
                absenceStatusRepository.save(absenceStatusToAdd);
            }
        }

        absenceStatusRepository.flush();
    }
}
