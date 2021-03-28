package mk.ukim.finki.mihail.risteski.teamout.model.request;

public class CreateOrganizationRequest
{
    public String Name;
    public String OrganizationCity;
    public String OrganizationStreet;
    public String OrganizationStreetNumber;
    public String OrganizationCountry;
    public String LogoName;
    public byte[] LogoContent;
    
    public String FirstName;
    public String LastName;
    public String Email;
    public CharSequence Password;
    public String PhoneNumber;
    public String UserCity;
    public String UserStreet;
    public String UserStreetNumber;
    public String UserCountry;
    public String PictureName;
    public byte[] PictureContent;

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getOrganizationCity()
    {
        return OrganizationCity;
    }

    public void setOrganizationCity(String organizationCity)
    {
        OrganizationCity = organizationCity;
    }

    public String getOrganizationStreet()
    {
        return OrganizationStreet;
    }

    public void setOrganizationStreet(String organizationStreet)
    {
        OrganizationStreet = organizationStreet;
    }

    public String getOrganizationStreetNumber()
    {
        return OrganizationStreetNumber;
    }

    public void setOrganizationStreetNumber(String organizationStreetNumber)
    {
        OrganizationStreetNumber = organizationStreetNumber;
    }

    public String getOrganizationCountry()
    {
        return OrganizationCountry;
    }

    public void setOrganizationCountry(String organizationCountry)
    {
        OrganizationCountry = organizationCountry;
    }

    public String getLogoName()
    {
        return LogoName;
    }

    public void setLogoName(String logoName)
    {
        LogoName = logoName;
    }

    public byte[] getLogoContent()
    {
        return LogoContent;
    }

    public void setLogoContent(byte[] logoContent)
    {
        LogoContent = logoContent;
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

    public CharSequence getPassword()
    {
        return Password;
    }

    public void setPassword(CharSequence password)
    {
        Password = password;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        PhoneNumber = phoneNumber;
    }

    public String getUserCity()
    {
        return UserCity;
    }

    public void setUserCity(String userCity)
    {
        UserCity = userCity;
    }

    public String getUserStreet()
    {
        return UserStreet;
    }

    public void setUserStreet(String userStreet)
    {
        UserStreet = userStreet;
    }

    public String getUserStreetNumber()
    {
        return UserStreetNumber;
    }

    public void setUserStreetNumber(String userStreetNumber)
    {
        UserStreetNumber = userStreetNumber;
    }

    public String getUserCountry()
    {
        return UserCountry;
    }

    public void setUserCountry(String userCountry)
    {
        UserCountry = userCountry;
    }

    public String getPictureName()
    {
        return PictureName;
    }

    public void setPictureName(String pictureName)
    {
        PictureName = pictureName;
    }

    public byte[] getPictureContent()
    {
        return PictureContent;
    }

    public void setPictureContent(byte[] pictureContent)
    {
        PictureContent = pictureContent;
    }
}
