package mk.ukim.finki.mihail.risteski.teamout.model.auth;

public class EmployeeUser
{
    private Long Id;

    private int HolidayDaysUsed;
    private int HolidayDaysBalance;
    private int SickleaveDaysUsed;
    private int SickleaveDaysBalance;
    private int ExtraordinaryDaysUsed;
    private int ExtraordinaryDaysBalance;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public int getHolidayDaysUsed()
    {
        return HolidayDaysUsed;
    }

    public void setHolidayDaysUsed(int holidayDaysUsed)
    {
        HolidayDaysUsed = holidayDaysUsed;
    }

    public int getHolidayDaysBalance()
    {
        return HolidayDaysBalance;
    }

    public void setHolidayDaysBalance(int holidayDaysBalance)
    {
        HolidayDaysBalance = holidayDaysBalance;
    }

    public int getSickleaveDaysUsed()
    {
        return SickleaveDaysUsed;
    }

    public void setSickleaveDaysUsed(int sickleaveDaysUsed)
    {
        SickleaveDaysUsed = sickleaveDaysUsed;
    }

    public int getSickleaveDaysBalance()
    {
        return SickleaveDaysBalance;
    }

    public void setSickleaveDaysBalance(int sickleaveDaysBalance)
    {
        SickleaveDaysBalance = sickleaveDaysBalance;
    }

    public int getExtraordinaryDaysUsed()
    {
        return ExtraordinaryDaysUsed;
    }

    public void setExtraordinaryDaysUsed(int extraordinaryDaysUsed)
    {
        ExtraordinaryDaysUsed = extraordinaryDaysUsed;
    }

    public int getExtraordinaryDaysBalance()
    {
        return ExtraordinaryDaysBalance;
    }

    public void setExtraordinaryDaysBalance(int extraordinaryDaysBalance)
    {
        ExtraordinaryDaysBalance = extraordinaryDaysBalance;
    }
}
