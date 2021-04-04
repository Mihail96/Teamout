package mk.ukim.finki.mihail.risteski.teamout.model.dto;

import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceStatusEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceTypeEnum;

import java.util.Date;

public class AbsenceDto
{
    private Date DateFrom;

    private Date DateTo;

    private AbsenceStatusEnum AbsenceStatusEnum;

    private AbsenceTypeEnum AbsenceTypeEnum;

    public Date getDateFrom()
    {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom)
    {
        DateFrom = dateFrom;
    }

    public Date getDateTo()
    {
        return DateTo;
    }

    public void setDateTo(Date dateTo)
    {
        DateTo = dateTo;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceStatusEnum getAbsenceStatusEnum()
    {
        return AbsenceStatusEnum;
    }

    public void setAbsenceStatusEnum(
            mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceStatusEnum absenceStatusEnum)
    {
        AbsenceStatusEnum = absenceStatusEnum;
    }

    public mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceTypeEnum getAbsenceTypeEnum()
    {
        return AbsenceTypeEnum;
    }

    public void setAbsenceTypeEnum(
            mk.ukim.finki.mihail.risteski.teamout.model.enumeration.AbsenceTypeEnum absenceTypeEnum)
    {
        AbsenceTypeEnum = absenceTypeEnum;
    }
}
