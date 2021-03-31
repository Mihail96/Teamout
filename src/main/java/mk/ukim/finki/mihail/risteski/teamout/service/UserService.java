package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Address;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Image;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.request.UserUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.AddressRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.ImageRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.UserRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService implements IUserService
{
    private final UserRepository _userRepository;
    private final AddressRepository _addressRepository;
    private final ImageRepository _imageRepository;

    public UserService(UserRepository userRepository,
                       AddressRepository addressRepository,
                       ImageRepository imageRepository)
    {
        _userRepository = userRepository;
        _addressRepository = addressRepository;
        _imageRepository = imageRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return _userRepository.GetUserByEmail(email);
    }

    @Override
    public UserDto GetUserProfile(Long organizationId, Long userId)
    {
        return getUserDto(_userRepository.GetUser(organizationId, userId));
    }

    @Override
    public UserDto UpdateUser(Long organizationId, Long userId, UserUpdateRequest request, MultipartFile pictureFile)
    {
        if(pictureFile != null && pictureFile.getOriginalFilename() != null)
        {
            request.setPictureName(StringUtils.cleanPath(pictureFile.getOriginalFilename()));
            try
            {
                request.setPictureContent(pictureFile.getBytes());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        User user = _userRepository.GetUser(organizationId, userId);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        Address address = user.getAddress();
        address.setCity(request.getUserCity());
        address.setStreet(request.getUserStreet());
        address.setNumber(request.getUserNumber());
        address.setCountry(request.getUserCountry());
        _addressRepository.save(address);

        if(request.getPictureName() != null && request.getPictureName() != null)
        {
            Image image = user.getPicture();
            if(image == null)
            {
                image = new Image();
            }
            image.setName(request.getPictureName());
            image.setContent(request.getPictureContent());
            _imageRepository.save(image);
        }

        _userRepository.saveAndFlush(user);

        return getUserDto(user);
    }

    private UserDto getUserDto(User user)
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
