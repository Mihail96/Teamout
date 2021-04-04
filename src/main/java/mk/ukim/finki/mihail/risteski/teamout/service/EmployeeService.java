package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.DraftUser;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.DraftUserRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.EmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.OrganizationRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmailService;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmployeeService;
import mk.ukim.finki.mihail.risteski.teamout.util.EmployeeUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService
{
    private final EmployeeRepository _employeeRepository;
    private final DraftUserRepository _draftUserRepository;
    private final OrganizationRepository _organizationRepository;
    private final IEmailService _emailService;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DraftUserRepository draftUserRepository,
                           OrganizationRepository organizationRepository,
                           IEmailService emailService)
    {
        _employeeRepository = employeeRepository;
        _draftUserRepository = draftUserRepository;
        _organizationRepository = organizationRepository;
        _emailService = emailService;
    }

    @Override
    public List<EmployeeDto> GetEmployeeList(Long organizationId)
    {
        return getEmployeeDtos(organizationId);
    }

    @Override
    public List<EmployeeDto> InviteEmployee(Long organizationId, DraftUserCreateRequest draftUserCreateRequest)
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
        draftUser.setRole(RoleEnum.ToRole(RoleEnum.Employee));

        _draftUserRepository.saveAndFlush(draftUser);

        String subject = "Invitation to make your account in Teamout";

        String text = "Hello, your organization " +
                organization.getName() +
                " has invited you to create you account.\n" +
                "You can follow this link to create your user: " +
                "http://localhost:5555" +
                "/register" +
                "/user/" +
                generatedString +
                " \n" +
                " \n" +
                "Teamout";
        _emailService.SendSimpleMessage(draftUserCreateRequest.getEmail(), subject, text);

        return getEmployeeDtos(organizationId);
    }

    private List<EmployeeDto> getEmployeeDtos(Long organizationId)
    {
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        List<Employee> employees = _employeeRepository.GetEmployeesInOrganization(organizationId);
        for (Employee employee : employees)
        {
            employeeDtos.add(EmployeeUtils.CreateEmployeeDto(employee));
        }
        return employeeDtos;
    }
}
