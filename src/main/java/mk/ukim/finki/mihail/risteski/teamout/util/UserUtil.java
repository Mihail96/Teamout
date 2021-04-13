package mk.ukim.finki.mihail.risteski.teamout.util;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Address;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Image;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;

public class UserUtil
{
    public static UserDto CreateUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());

        Address address = user.getAddress();
        userDto.setUserCity(address.getCity());
        userDto.setUserStreet(address.getStreet());
        userDto.setUserNumber(address.getNumber());
        userDto.setUserCountry(address.getCountry());

        Image image = user.getPicture();
        if (image != null)
        {
            userDto.setPictureId(image.getId());
        }
        return userDto;
    }
}
