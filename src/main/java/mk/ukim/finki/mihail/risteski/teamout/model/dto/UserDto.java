package mk.ukim.finki.mihail.risteski.teamout.model.dto;

public class UserDto
{
    private String FirstName;

    private String LastName;

    private String Email;

    private String PhoneNumber;

    private String UserStreet;

    private String UserNumber;

    private String UserCity;

    private String UserCountry;

    private Long PictureId;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUserStreet() {
        return UserStreet;
    }

    public void setUserStreet(String userStreet) {
        UserStreet = userStreet;
    }

    public String getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(String userNumber) {
        UserNumber = userNumber;
    }

    public String getUserCity() {
        return UserCity;
    }

    public void setUserCity(String userCity) {
        UserCity = userCity;
    }

    public String getUserCountry() {
        return UserCountry;
    }

    public void setUserCountry(String userCountry) {
        UserCountry = userCountry;
    }

    public Long getPictureId() {
        return PictureId;
    }

    public void setPictureId(Long pictureId) {
        PictureId = pictureId;
    }
}
