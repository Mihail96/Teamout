package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDetailsDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.DraftUser;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.EmployeeUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.DraftUserRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.EmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.OrganizationRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmailService;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmployeeService;
import mk.ukim.finki.mihail.risteski.teamout.util.EmployeeUtils;
import mk.ukim.finki.mihail.risteski.teamout.util.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static mk.ukim.finki.mihail.risteski.teamout.util.EmployeeUtils.CreateEmployeeDetailsDto;

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

    @Override
    public EmployeeDetailsDto GetEmployeeDetails(Long organizationId, Long employeeId)
    {
        return EmployeeUtils.CreateEmployeeDetailsDto(_employeeRepository.GetEmployeeById(employeeId));
    }

    @Override
    public List<EmployeeDto> GetEmployeeNotResponsible(Long organizationId, Long employeeId)
    {
        List<Employee> employees = _employeeRepository.GetEmployeesInOrganization(organizationId);
        Employee dbEmployee = _employeeRepository.GetEmployeeById(employeeId);

        employees = employees.stream()
                             .filter(x -> !dbEmployee.getId().equals(x.getId()) &&
                                           dbEmployee.getResponsibleTo().stream().noneMatch(y -> y.getId().equals(x.getId())) &&
                                           dbEmployee.getResponsibleFor().stream().noneMatch(y -> y.getId().equals(x.getId())))
                             .collect(Collectors.toList());

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees)
        {
            employeeDtos.add(EmployeeUtils.CreateEmployeeDto(employee));
        }

        return employeeDtos;
    }

    @Override
    public void AddEmployeeResponsibleTo(Long organizationId, Long employeeId, Long[] employeeIds)
    {
        List<Employee> dbEmployees = _employeeRepository.GetEmployeeByIds(employeeIds);

        for (Employee dbCurrentEmployee: dbEmployees)
        {
            Employee employee = new Employee();
            List<Employee> employees = new ArrayList<>();
            employee.setId(employeeId);
            employees.add(employee);
            dbCurrentEmployee.setResponsibleFor(employees);
            _employeeRepository.save(dbCurrentEmployee);
        }

        _employeeRepository.flush();
    }

    @Override
    public void AddEmployeeResponsibleFor(Long organizationId, Long employeeId, Long[] employeeIds)
    {
        Employee dbEmployee = _employeeRepository.GetEmployeeById(employeeId);

        List<Employee> employees = new ArrayList<>();
        for (Long currentEmployeeId: employeeIds)
        {
            Employee respEmployee = new Employee();
            //respEmployee.setId(currentEmployeeId);
            //employees.add(respEmployee);

            List<Employee> dbEmployees = new ArrayList<>();
            dbEmployees.add(dbEmployee);
            respEmployee.setResponsibleFor(dbEmployees);
        }

        dbEmployee.setResponsibleFor(employees);

        _employeeRepository.saveAndFlush(dbEmployee);
    }

    @Override
    public void UpdateEmployeeDetails(Long organizationId, Long employeeId, EmployeeUpdateRequest employeeUpdateRequest)
    {
        Employee employee = _employeeRepository.GetEmployeeById(employeeId);

        employee.setHolidayDaysBalance(employeeUpdateRequest.getHolidayDaysBalance());
        employee.setHolidayDaysUsed(employeeUpdateRequest.getHolidayDaysUsed());
        employee.setSickleaveDaysBalance(employeeUpdateRequest.getSickleaveDaysBalance());
        employee.setSickleaveDaysUsed(employeeUpdateRequest.getSickleaveDaysUsed());
        employee.setExtraordinaryDaysBalance(employeeUpdateRequest.getExtraordinaryDaysBalance());
        employee.setExtraordinaryDaysUsed(employeeUpdateRequest.getExtraordinaryDaysUsed());

        _employeeRepository.saveAndFlush(employee);
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
