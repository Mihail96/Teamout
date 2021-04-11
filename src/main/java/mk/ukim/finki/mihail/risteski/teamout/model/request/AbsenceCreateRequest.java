package mk.ukim.finki.mihail.risteski.teamout.model.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AbsenceCreateRequest
{
    private String AbsenceType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateTo;

    public String getAbsenceType()
    {
        return AbsenceType;
    }

    public void setAbsenceType(String absenceType)
    {
        AbsenceType = absenceType;
    }

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
}
