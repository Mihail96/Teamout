package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Address;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Image;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.repository.UserRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService
{
    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return _userRepository.GetUserByEmail(email);
    }

    @Override
    public UserDto GetUserProfile(Long organizationId, Long userId)
    {
        User user = _userRepository.GetUser(organizationId, userId);

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
        if(image != null)
        {
            userDto.setPictureId(image.getId());
        }

        return userDto;
    }
}
