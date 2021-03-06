package mk.ukim.finki.mihail.risteski.teamout.model.dto;

public class OrganizationDto
{
    private String Name;
    private String OrganizationCity;
    private String OrganizationStreet;
    private String OrganizationStreetNumber;
    private String OrganizationCountry;
    private Long ImageId;

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

    public Long getImageId() {
        return ImageId;
    }

    public void setImageId(Long imageId) {
        ImageId = imageId;
    }
}
