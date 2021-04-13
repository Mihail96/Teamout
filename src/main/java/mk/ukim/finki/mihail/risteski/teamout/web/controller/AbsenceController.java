package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.AbsenceCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAbsenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.ValidationException;
import java.util.List;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping
public class AbsenceController
{
    private final IAbsenceService _absenceService;

    public AbsenceController(IAbsenceService absenceService)
    {
        _absenceService = absenceService;
    }

    @GetMapping(value = "/organization/{organizationId}/absences")
    public String GetAbsences(@PathVariable(value="organizationId") Long organizationId,
                              Model model) throws NotFoundException
    {
        List<AbsenceDto> absenceDtos = _absenceService.GetAbsences(organizationId);

        model.addAttribute("absenceDtos", absenceDtos);
        model.addAttribute("bodyContent", "list-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/organization/{organizationId}/employee/{employeeId}/absences")
    public String GetEmployeeAbsences(@PathVariable(value="organizationId") Long organizationId,
                                      @PathVariable(value="employeeId") Long employeeId,
                                      Model model) throws NotFoundException
    {
        List<AbsenceDto> absenceDtos = _absenceService.GetEmployeeAbsences(organizationId, employeeId);

        model.addAttribute("absenceDtos", absenceDtos);
        model.addAttribute("bodyContent", "list-employee-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/organization/{organizationId}/employee/{employeeId}/absences/approval")
    public String GetAbsencesForApproval(@PathVariable(value="organizationId") Long organizationId,
                                         @PathVariable(value="employeeId") Long employeeId,
                                         Model model) throws NotFoundException
    {
        List<AbsenceDto> absenceDtos = _absenceService.GetAbsencesForApproval(organizationId, employeeId);

        model.addAttribute("absenceDtos", absenceDtos);
        model.addAttribute("bodyContent", "approval-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/organization/{organizationId}/absence/{absenceId}")
    public String GetAbsence(@PathVariable(value="organizationId") Long organizationId,
                             @PathVariable(value="absenceId") Long absenceId,
                             Model model) throws NotFoundException
    {
        AbsenceDto absenceDto = new AbsenceDto();
        try
        {
            absenceDto = _absenceService.GetAbsence(organizationId, absenceId);
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("absenceDto", absenceDto);
        model.addAttribute("bodyContent", "details-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/organization/{organizationId}/employee/{employeeId}/absence/create")
    public String GetCreateAbsence(@PathVariable(value="organizationId") Long organizationId,
                                   @PathVariable(value="employeeId") Long employeeId,
                                   Model model)
    {
        model.addAttribute("bodyContent", "create-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/organization/{organizationId}/employee/{employeeId}/absence/create")
    public String CreateEmployeeAbsence(@PathVariable(value="organizationId") Long organizationId,
                                        @PathVariable(value="employeeId") Long employeeId,
                                        AbsenceCreateRequest request,
                                        Model model)
    {
        try
        {
            _absenceService.CreateAbsence(organizationId, employeeId, request);
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/organization/"+ organizationId.toString() +"/employee/"+ employeeId.toString() +"/absences";
    }

    @PostMapping(value = "/organization/{organizationId}/employee/{employeeId}/absence/{absenceId}/delete")
    public String DeleteEmployeeAbsence(@PathVariable(value="organizationId") Long organizationId,
                                        @PathVariable(value="employeeId") Long employeeId,
                                        @PathVariable(value="absenceId") Long absenceId,
                                        Model model)
    {
        _absenceService.DeleteAbsence(organizationId, employeeId, absenceId);

        return "redirect:/organization/"+ organizationId.toString() +"/employee/"+ employeeId.toString() +"/absences";
    }

    @PostMapping(value = "/organization/{organizationId}/employee/{employeeId}/absence/{absenceId}/approve")
    public String ApproveEmployeeAbsence(@PathVariable(value="organizationId") Long organizationId,
                                         @PathVariable(value="employeeId") Long employeeId,
                                         @PathVariable(value="absenceId") Long absenceId,
                                         Model model)
    {
        try
        {
            _absenceService.ApproveAbsence(organizationId, employeeId, absenceId);
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/organization/"+ organizationId.toString() +"/employee/"+ employeeId.toString() +"/absences/approval";
    }

    @PostMapping(value = "/organization/{organizationId}/employee/{employeeId}/absence/{absenceId}/reject")
    public String RejectEmployeeAbsence(@PathVariable(value="organizationId") Long organizationId,
                                        @PathVariable(value="employeeId") Long employeeId,
                                        @PathVariable(value="absenceId") Long absenceId,
                                        Model model)
    {
        _absenceService.RejectAbsence(organizationId, employeeId, absenceId);

        return "redirect:/organization/"+ organizationId.toString() +"/employee/"+ employeeId.toString() +"/absences/approval";
    }
}
