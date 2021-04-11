package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDetailsDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.EmployeeResponsibleRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/organization/{organizationId}/employee")
public class EmployeeController
{
    private final IEmployeeService _employeeService;

    public EmployeeController(IEmployeeService employeeService)
    {
        _employeeService = employeeService;
    }

    @GetMapping(value = "/list")
    public String GetEmployeeList(@PathVariable(value="organizationId") Long organizationId,
                                  Model model)
    {
        List<EmployeeDto> employeeDtos = _employeeService.GetEmployeeList(organizationId);

        model.addAttribute("employeeDtos", employeeDtos);
        model.addAttribute("bodyContent", "list-employee");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/invite")
    public String GetInviteEmployee(@PathVariable(value="organizationId") Long organizationId,
                                    Model model)
    {
        model.addAttribute("bodyContent", "invite-employee");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/{employeeId}")
    public String GetEmployeeDetails(@PathVariable(value="organizationId") Long organizationId,
                                     @PathVariable(value="employeeId") Long employeeId,
                                     Model model)
    {
        EmployeeDetailsDto employeeDetailsDto = _employeeService.GetEmployeeDetails(organizationId, employeeId);

        model.addAttribute("employeeDetailsDto", employeeDetailsDto);
        model.addAttribute("bodyContent", "details-employee");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/{employeeId}/add-responsible-to")
    public String GetEmployeeAddResponsibleTo(@PathVariable(value="organizationId") Long organizationId,
                                              @PathVariable(value="employeeId") Long employeeId,
                                              Model model)
    {
        List<EmployeeDto> employeeDtos = _employeeService.GetEmployeeNotResponsible(organizationId, employeeId);

        model.addAttribute("employeeDtos", employeeDtos);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("bodyContent", "add-responsible-to-employee");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/{employeeId}/add-responsible-for")
    public String GetEmployeeAddResponsibleFor(@PathVariable(value="organizationId") Long organizationId,
                                              @PathVariable(value="employeeId") Long employeeId,
                                              Model model)
    {
        List<EmployeeDto> employeeDtos = _employeeService.GetEmployeeNotResponsible(organizationId, employeeId);

        model.addAttribute("employeeDtos", employeeDtos);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("bodyContent", "add-responsible-for-employee");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/{employeeId}/add-responsible-to")
    public String GetEmployeeAddResponsibleTo(@PathVariable(value="organizationId") Long organizationId,
                                              @PathVariable(value="employeeId") Long employeeId,
                                              @RequestParam(value = "EmployeeIds") Long[] employeeIds,
                                              Model model)
    {
        _employeeService.AddEmployeeResponsibleTo(organizationId, employeeId, employeeIds);

        model.addAttribute("bodyContent", "add-responsible-to-employee");
        HandleBaseAttributes(model);

        return "redirect:/organization/"+ organizationId.toString() +"/employee/"+ employeeId.toString();
    }

    @PostMapping(value = "/{employeeId}/add-responsible-for")
    public String GetEmployeeAddResponsibleFor(@PathVariable(value="organizationId") Long organizationId,
                                               @PathVariable(value="employeeId") Long employeeId,
                                               @RequestParam(value = "EmployeeIds") Long[] employeeIds,
                                               Model model)
    {
        _employeeService.AddEmployeeResponsibleFor(organizationId, employeeId, employeeIds);

        model.addAttribute("bodyContent", "details-employee");
        HandleBaseAttributes(model);

        return "redirect:/organization/"+ organizationId.toString() +"/employee/"+ employeeId.toString();
    }

    @PostMapping(value = "/invite")
    public String InviteEmployee(@PathVariable(value="organizationId") Long organizationId,
                                 DraftUserCreateRequest draftUserCreateRequest,
                                 Model model)
    {
        List<EmployeeDto> employeeDtos = _employeeService.InviteEmployee(organizationId, draftUserCreateRequest);

        model.addAttribute("employeeDtos", employeeDtos);
        model.addAttribute("bodyContent", "list-employee");
        HandleBaseAttributes(model);

        return "root";
    }
}
