package mk.ukim.finki.mihail.risteski.teamout.service;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.*;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.*;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IOrganizationService;
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
    public void CreateOrganization(OrganizationCreateRequest request, MultipartFile logoFile, MultipartFile userImageFile)
    {
        if(logoFile != null && logoFile.getOriginalFilename() != null)
        {
            request.setLogoName(StringUtils.cleanPath(logoFile.getOriginalFilename()));
            try
            {
                request.setLogoContent(logoFile.getBytes());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if(userImageFile != null && userImageFile.getOriginalFilename() != null)
        {
            request.setPictureName(StringUtils.cleanPath(userImageFile.getOriginalFilename()));
            try
            {
                request.setPictureContent(userImageFile.getBytes());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Organization organization = new Organization();

        organization.setName(request.getName());

        Address organizationAddress = new Address();
        organizationAddress.setCity(request.getOrganizationCity());
        organizationAddress.setStreet(request.getOrganizationStreet());
        organizationAddress.setNumber(request.getOrganizationStreetNumber());
        organizationAddress.setCountry(request.getOrganizationCountry());

        organization.setAddress(organizationAddress);

        UserInOrganization owner = new UserInOrganization();
        owner.setRole(RoleEnum.ToRole(RoleEnum.Owner));

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

        if(!request.getLogoName().equals("") && request.getLogoContent().length != 0)
        {
            Image logo = new Image();
            logo.setName(request.getLogoName());
            logo.setContent(request.getLogoContent());
            organization.setLogo(logo);
            _imageRepository.save(logo);
        }

        if(!request.getPictureName().equals("") && request.getPictureContent().length != 0)
        {
            Image picture = new Image();
            picture.setName(request.getPictureName());
            picture.setContent(request.getPictureContent());
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
    public OrganizationDto GetOrganizationProfile(Long organizationId) throws NotFoundException
    {
        return getOrganizationDto(GetOrganization(organizationId));
    }

    @Override
    public OrganizationDto UpdateOrganization(Long organizationId,
                                              OrganizationUpdateRequest request,
                                              MultipartFile logoFile) throws NotFoundException
    {
        if(logoFile != null && logoFile.getOriginalFilename() != null)
        {
            request.setLogoName(StringUtils.cleanPath(logoFile.getOriginalFilename()));
            try
            {
                request.setLogoContent(logoFile.getBytes());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Organization organization = GetOrganization(organizationId);

        Address address = organization.getAddress();
        Image image = organization.getLogo();

        organization.setName(request.getName());

        address.setCity(request.getOrganizationCity());
        address.setStreet(request.getOrganizationStreet());
        address.setNumber(request.getOrganizationStreetNumber());
        address.setCountry(request.getOrganizationCountry());
        _addressRepository.save(address);

        if(!request.getLogoName().equals("") && request.getLogoContent().length != 0)
        {
            image.setName(request.getLogoName());
            image.setContent(request.getLogoContent());
            _imageRepository.save(image);
        }

        _organizationRepository.saveAndFlush(organization);

        return getOrganizationDto(organization);
    }

    private OrganizationDto getOrganizationDto(Organization organization)
    {
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

    private Organization GetOrganization(Long id) throws NotFoundException
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
}
