package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.DraftUser;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.AdminRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.DraftUserRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.OrganizationRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAdminService;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmailService;
import mk.ukim.finki.mihail.risteski.teamout.util.UserUtil;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AdminService implements IAdminService
{
    private final AdminRepository _adminRepository;
    private final OrganizationRepository _organizationRepository;
    private final DraftUserRepository _draftUserRepository;
    private final IEmailService _emailService;

    public AdminService(AdminRepository adminRepository,
                        OrganizationRepository organizationRepository,
                        DraftUserRepository draftUserRepository,
                        IEmailService emailService)
    {
        _adminRepository = adminRepository;
        _organizationRepository = organizationRepository;
        _draftUserRepository = draftUserRepository;
        _emailService = emailService;
    }

    @Override
    public List<UserDto> GetAdminList(Long organizationId)
    {
        return getAdminDtos(organizationId);
    }

    @Override
    public List<UserDto> InviteAdmin(Long organizationId, DraftUserCreateRequest draftUserCreateRequest)
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String generatedString = encoder.encodeToString(bytes);

        Organization organization = _organizationRepository.GetOrganizationById(organizationId);

        DraftUser draftUser = new DraftUser();
        draftUser.setActivationCode(generatedString);
        draftUser.setEmail(draftUserCreateRequest.getEmail());
        draftUser.setFirstName(draftUserCreateRequest.getFirstName());
        draftUser.setLastName(draftUserCreateRequest.getLastName());
        draftUser.setOrganization(organization);
        draftUser.setRole(RoleEnum.ToRole(RoleEnum.Admin));

        _draftUserRepository.saveAndFlush(draftUser);

        String subject = "Invitation to make your account in Teamout";

        String text = "Hello, your organization " +
                organization.getName() +
                " has invited you to create you account as an admin.\n" +
                "You can follow this link to create your user: " +
                "http://localhost:5555" +
                "/register" +
                "/user/" +
                generatedString +
                " \n" +
                " \n" +
                "Teamout";
        _emailService.SendSimpleMessage(draftUserCreateRequest.getEmail(), subject, text);

        return getAdminDtos(organizationId);
    }

    private List<UserDto> getAdminDtos(Long organizationId)
    {
        List<UserDto> adminDtos = new ArrayList<>();

        List<User> admins = _adminRepository.GetAdminsInOrganization(organizationId, RoleEnum.Admin.getName());
        for (User user : admins)
        {
            adminDtos.add(UserUtil.CreateUserDto(user));
        }
        return adminDtos;
    }
}
