package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.DraftEmployee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.request.EmployeeCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.repository.DraftEmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.EmployeeRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.OrganizationRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmailService;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmployeeService;
import mk.ukim.finki.mihail.risteski.teamout.util.EmployeeUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService implements IEmployeeService
{
    private final EmployeeRepository _employeeRepository;
    private final DraftEmployeeRepository _draftEmployeeRepository;
    private final OrganizationRepository _organizationRepository;
    private final IEmailService _emailService;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DraftEmployeeRepository draftEmployeeRepository,
                           OrganizationRepository organizationRepository,
                           IEmailService emailService)
    {
        _employeeRepository = employeeRepository;
        _draftEmployeeRepository = draftEmployeeRepository;
        _organizationRepository = organizationRepository;
        _emailService = emailService;
    }

    @Override
    public List<EmployeeDto> GetEmployeeList(Long organizationId)
    {
        return getEmployeeDtos(organizationId);
    }

    @Override
    public List<EmployeeDto> InviteEmployee(Long organizationId, EmployeeCreateRequest employeeCreateRequest)
    {
        byte[] array = new byte[16];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);

        DraftEmployee draftEmployee = new DraftEmployee();
        draftEmployee.setActivationCode(generatedString);

        _draftEmployeeRepository.saveAndFlush(draftEmployee);

        Organization organization = _organizationRepository.GetOrganizationById(organizationId);
        String subject = "Invitation to make your account in Teamout";

        String text = "Hello, your organization " +
                organization.getName() +
                " has invited you to create you account.\n" +
                "You can follow this link to create your user: " +
                "http://localhost:5555" +
                "/register" +
                "/employee/" +
                generatedString +
                " \n" +
                " \n" +
                "Teamout";
        _emailService.SendSimpleMessage(employeeCreateRequest.getEmail(), subject, text);

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
