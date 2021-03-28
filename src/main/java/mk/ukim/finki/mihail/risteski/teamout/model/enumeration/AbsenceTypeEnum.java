package mk.ukim.finki.mihail.risteski.teamout.model.enumeration;

import java.util.List;

public class AbsenceTypeEnum extends Enumeration
{
    public static final AbsenceTypeEnum None = new AbsenceTypeEnum(-1L, "NONE");
    public static final AbsenceTypeEnum Holiday = new AbsenceTypeEnum(0L, "HOLIDAY");
    public static final AbsenceTypeEnum Sickleave = new AbsenceTypeEnum(1L, "SICKLEAVE");
    public static final AbsenceTypeEnum ExtraOrdinary = new AbsenceTypeEnum(2L, "EXTRAORDINARY");

    protected AbsenceTypeEnum(Long id, String name) {
        super(id, name);
    }

    public static List<AbsenceTypeEnum> GetAll()
    {
        return All(None);
    }
}
