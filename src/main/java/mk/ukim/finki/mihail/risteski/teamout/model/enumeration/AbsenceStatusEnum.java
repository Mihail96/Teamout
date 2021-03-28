package mk.ukim.finki.mihail.risteski.teamout.model.enumeration;

import java.util.List;

public class AbsenceStatusEnum extends Enumeration
{
    public static final AbsenceStatusEnum None = new AbsenceStatusEnum(-1L, "NONE");
    public static final AbsenceStatusEnum Pending = new AbsenceStatusEnum(0L, "PENDING");
    public static final AbsenceStatusEnum Approved = new AbsenceStatusEnum(1L, "APPROVED");
    public static final AbsenceStatusEnum Rejected = new AbsenceStatusEnum(2L, "REJECTED");

    private AbsenceStatusEnum(Long id, String name) {
        super(id, name);
    }

    public static List<AbsenceStatusEnum> GetAll()
    {
        return All(None);
    }
}
