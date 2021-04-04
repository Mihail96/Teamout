package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
