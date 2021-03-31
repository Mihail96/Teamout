package mk.ukim.finki.mihail.risteski.teamout.model.request;

public class OrganizationUpdateRequest
{
    private String Name;
    private String OrganizationCity;
    private String OrganizationStreet;
    private String OrganizationStreetNumber;
    private String OrganizationCountry;
    private String LogoName;
    private byte[] LogoContent;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrganizationCity() {
        return OrganizationCity;
    }

    public void setOrganizationCity(String organizationCity) {
        OrganizationCity = organizationCity;
    }

    public String getOrganizationStreet() {
        return OrganizationStreet;
    }

    public void setOrganizationStreet(String organizationStreet) {
        OrganizationStreet = organizationStreet;
    }

    public String getOrganizationStreetNumber() {
        return OrganizationStreetNumber;
    }

    public void setOrganizationStreetNumber(String organizationStreetNumber) {
        OrganizationStreetNumber = organizationStreetNumber;
    }

    public String getOrganizationCountry() {
        return OrganizationCountry;
    }

    public void setOrganizationCountry(String organizationCountry) {
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
}
