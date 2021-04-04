package mk.ukim.finki.mihail.risteski.teamout.model.dto;

public class DraftUserDto
{
    private String ActivationCode;
    private String FirstName;
    private String LastName;
    private String Email;

    public String getActivationCode()
    {
        return ActivationCode;
    }

    public void setActivationCode(String activationCode)
    {
        ActivationCode = activationCode;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }
}
