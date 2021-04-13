package mk.ukim.finki.mihail.risteski.teamout.service;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.DraftUserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.*;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.UserUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.*;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IUserService;
import mk.ukim.finki.mihail.risteski.teamout.util.UserUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService implements IUserService
{
    private final PasswordEncoder _passwordEncoder;
    private final UserRepository _userRepository;
    private final DraftUserRepository _draftUserRepository;
    private final UserInOrganizationRepository _userInOrganizationRepository;
    private final AddressRepository _addressRepository;
    private final EmployeeRepository _employeeRepository;
    private final ImageRepository _imageRepository;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       DraftUserRepository draftUserRepository,
                       UserInOrganizationRepository userInOrganizationRepository,
                       AddressRepository addressRepository,
                       EmployeeRepository employeeRepository,
                       ImageRepository imageRepository)
    {
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
        _draftUserRepository = draftUserRepository;
        _userInOrganizationRepository = userInOrganizationRepository;
        _addressRepository = addressRepository;
        _employeeRepository = employeeRepository;
        _imageRepository = imageRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return _userRepository.GetUserByEmail(email);
    }

    @Override
    public UserDto GetUserProfile(Long userId)
    {
        return UserUtil.CreateUserDto(_userRepository.GetUser(userId));
    }

    @Override
    public void CreateUser(DraftUserCreateRequest request, MultipartFile pictureFile)
    {
        DraftUser draftUser = _draftUserRepository.GetDraftUserByActivationCode(request.getActivationCode());
        if(pictureFile != null &&
           pictureFile.getOriginalFilename() != null &&
           !pictureFile.getOriginalFilename().equals(""))
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

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(_passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        Address userAddress = new Address();
        userAddress.setCity(request.getUserCity());
        userAddress.setStreet(request.getUserStreet());
        userAddress.setNumber(request.getUserStreetNumber());
        userAddress.setCountry(request.getUserCountry());

        user.setAddress(userAddress);

        if(request.getPictureName() != null && request.getPictureName() != null)
        {
            Image picture = new Image();
            picture.setName(request.getPictureName());
            picture.setContent(request.getPictureContent());
            user.setPicture(picture);
            _imageRepository.save(picture);
        }

        UserInOrganization userInOrganization = new UserInOrganization();
        userInOrganization.setUser(user);
        userInOrganization.setOrganization(draftUser.getOrganization());
        userInOrganization.setRole(draftUser.getRole());
        if(draftUser.getRole().getName().equals(RoleEnum.Employee.getName()))
        {
            Employee employee = new Employee();
            userInOrganization.setEmployee(employee);
            _employeeRepository.save(employee);
        }

        _draftUserRepository.delete(draftUser);
        _addressRepository.save(userAddress);
        _userRepository.save(user);
        _userInOrganizationRepository.saveAndFlush(userInOrganization);
    }

    @Override
    public UserDto UpdateUser(Long userId, UserUpdateRequest request, MultipartFile pictureFile)
    {
        if(pictureFile != null &&
           pictureFile.getOriginalFilename() != null &&
          !pictureFile.getOriginalFilename().equals(""))
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

        User user = _userRepository.GetUser(userId);

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

        if(request.getPictureName() != null && !request.getPictureName().equals(""))
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

        return UserUtil.CreateUserDto(user);
    }

    @Override
    public DraftUserDto GetDraftUser(String activationCode) throws NotFoundException
    {
        DraftUser draftUser = _draftUserRepository.GetDraftUserByActivationCode(activationCode);
        if(draftUser == null)
        {
            throw new NotFoundException("Couldnt find the drafted user");
        }

        DraftUserDto draftUserDto = new DraftUserDto();
        draftUserDto.setActivationCode(activationCode);
        draftUserDto.setEmail(draftUser.getEmail());
        draftUserDto.setFirstName(draftUser.getFirstName());
        draftUserDto.setLastName(draftUser.getLastName());

        return draftUserDto;
    }
}
