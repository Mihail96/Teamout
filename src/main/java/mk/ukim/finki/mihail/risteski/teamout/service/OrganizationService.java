package mk.ukim.finki.mihail.risteski.teamout.service;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.*;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.CreateOrganizationRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.*;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IOrganizationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class OrganizationService implements IOrganizationService
{
    private final PasswordEncoder _passwordEncoder;
    private final OrganizationRepository _organizationRepository;
    private final AddressRepository _addressRepository;
    private final ImageRepository _imageRepository;
    private final UserRepository _userRepository;
    private final UserInOrganizationRepository _userInOrganizationRepository;

    public OrganizationService(PasswordEncoder passwordEncoder,
                               OrganizationRepository organizationRepository,
                               AddressRepository addressRepository,
                               ImageRepository imageRepository,
                               UserRepository userRepository,
                               UserInOrganizationRepository userInOrganizationRepository)
    {
        _passwordEncoder = passwordEncoder;
        _organizationRepository = organizationRepository;
        _addressRepository = addressRepository;
        _userRepository = userRepository;
        _imageRepository = imageRepository;
        _userInOrganizationRepository = userInOrganizationRepository;
    }

    @Override
    public Organization GetOrganization(Long id) throws NotFoundException
    {
        Optional<Organization> optionalOrganization = _organizationRepository.findById(id);
        if(optionalOrganization.isPresent())
        {
            return optionalOrganization.get();
        }
        else
        {
            throw new NotFoundException("Couldn't find the requested company by ID: " + id.toString());
        }
    }

    @Override
    public void CreateOrganization(CreateOrganizationRequest request, MultipartFile logoFile, MultipartFile userImageFile)
    {
        if(logoFile != null && logoFile.getOriginalFilename() != null)
        {
            request.LogoName = StringUtils.cleanPath(logoFile.getOriginalFilename());
            try
            {
                request.LogoContent = logoFile.getBytes();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if(userImageFile != null && userImageFile.getOriginalFilename() != null)
        {
            request.PictureName = StringUtils.cleanPath(userImageFile.getOriginalFilename());
            try
            {
                request.PictureContent = userImageFile.getBytes();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Organization organization = new Organization();

        organization.setName(request.Name);

        Address organizationAddress = new Address();
        organizationAddress.setCity(request.OrganizationCity);
        organizationAddress.setStreet(request.OrganizationStreet);
        organizationAddress.setNumber(request.OrganizationStreetNumber);
        organizationAddress.setCountry(request.OrganizationCountry);

        organization.setAddress(organizationAddress);

        UserInOrganization owner = new UserInOrganization();
        owner.setRole(RoleEnum.ToRole(RoleEnum.Owner));

        User user = new User();
        user.setFirstName(request.FirstName);
        user.setLastName(request.LastName);
        user.setEmail(request.Email);
        user.setPasswordHash(_passwordEncoder.encode(request.Password));
        user.setPhoneNumber(request.PhoneNumber);

        Address userAddress = new Address();
        userAddress.setCity(request.UserCity);
        userAddress.setStreet(request.UserStreet);
        userAddress.setNumber(request.UserStreetNumber);
        userAddress.setCountry(request.UserCountry);

        user.setAddress(userAddress);

        if(!request.LogoName.equals("") && request.LogoContent.length != 0)
        {
            Image logo = new Image();
            logo.setName(request.LogoName);
            logo.setContent(request.LogoContent);
            organization.setLogo(logo);
            _imageRepository.save(logo);
        }

        if(!request.PictureName.equals("") && request.PictureContent.length != 0)
        {
            Image picture = new Image();
            picture.setName(request.PictureName);
            picture.setContent(request.PictureContent);
            user.setPicture(picture);
            _imageRepository.save(picture);
        }

        owner.setUser(user);
        owner.setOrganization(organization);

        _addressRepository.save(userAddress);
        _addressRepository.save(organizationAddress);
        _userRepository.save(user);
        _organizationRepository.save(organization);
        _userInOrganizationRepository.saveAndFlush(owner);
    }

    @Override
    public OrganizationDto GetOrganizationProfile() throws NotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)authentication.getPrincipal();

        Optional<Organization> optionalOrganization =
                currentUser.getUserInOrganizations().stream().filter(x -> x.getRole().getName().equals(RoleEnum.Owner.getName()))
                        .map(x -> x.getOrganization()).findFirst();

        if(optionalOrganization.isEmpty())
        {
            throw new NotFoundException("Couldn't find organization");
        }

        Organization organization = optionalOrganization.get();

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName(organization.getName());
        organizationDto.setOrganizationCity(organization.getAddress().getCity());
        organizationDto.setOrganizationStreet(organization.getAddress().getStreet());
        organizationDto.setOrganizationStreetNumber(organization.getAddress().getNumber());
        organizationDto.setOrganizationCountry(organization.getAddress().getCountry());

        if (organization.getLogo() != null)
        {
            organizationDto.setImageId(organization.getLogo().getId());
        }

        return organizationDto;
    }

    @Override
    public OrganizationDto UpdateOrganization(Long organizationId,
                                              OrganizationUpdateRequest request,
                                              MultipartFile logoFile) throws NotFoundException
    {
        if(logoFile != null && logoFile.getOriginalFilename() != null)
        {
            request.LogoName = StringUtils.cleanPath(logoFile.getOriginalFilename());
            try
            {
                request.LogoContent = logoFile.getBytes();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Optional<Organization> optionalOrganization = _organizationRepository.findById(organizationId);
        if(optionalOrganization.isEmpty())
        {
            throw new NotFoundException("Couldn't find organization");
        }

        Organization organization = optionalOrganization.get();

        Address address = organization.getAddress();
        Image image = organization.getLogo();

        organization.setName(request.getName());

        address.setCity(request.getOrganizationCity());
        address.setStreet(request.getOrganizationStreet());
        address.setNumber(request.getOrganizationStreetNumber());
        address.setCountry(request.getOrganizationCountry());
        _addressRepository.save(address);

        if(!request.LogoName.equals("") && request.LogoContent.length != 0)
        {
            image.setName(request.getLogoName());
            image.setContent(request.getLogoContent());
            _imageRepository.save(image);
        }

        _organizationRepository.saveAndFlush(organization);

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName(organization.getName());
        organizationDto.setImageId(image.getId());
        organizationDto.setOrganizationCity(address.getCity());
        organizationDto.setOrganizationStreet(address.getStreet());
        organizationDto.setOrganizationStreetNumber(address.getNumber());
        organizationDto.setOrganizationCountry(address.getCountry());

        return organizationDto;
    }
}
