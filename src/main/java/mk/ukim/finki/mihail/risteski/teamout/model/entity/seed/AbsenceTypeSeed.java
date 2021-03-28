package mk.ukim.finki.mihail.risteski.teamout.model.entity.seed;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.AbsenceType;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceTypeEnum;
import mk.ukim.finki.mihail.risteski.teamout.repository.AbsenceTypeRepository;

import java.util.List;
import java.util.Optional;

public class AbsenceTypeSeed
{
    public static void SeedAbsenceTypes(AbsenceTypeRepository absenceTypeRepository)
    {
        List<AbsenceType> absenceTypes = absenceTypeRepository.findAll();
        List<AbsenceTypeEnum> absenceStatusEnums = AbsenceTypeEnum.GetAll();

        for (AbsenceTypeEnum absenceStatusEnum: absenceStatusEnums)
        {
            Optional<AbsenceType> absenceType =
                    absenceTypes.stream().filter(x -> x.getId().equals(absenceStatusEnum.getId())).findFirst();

            if(absenceType.isEmpty())
            {
                AbsenceType absenceTypeToAdd = new AbsenceType();
                absenceTypeToAdd.setId(absenceStatusEnum.getId());
                absenceTypeToAdd.setName(absenceStatusEnum.getName());
                absenceTypeRepository.save(absenceTypeToAdd);
            }
        }

        absenceTypeRepository.flush();
    }
}
