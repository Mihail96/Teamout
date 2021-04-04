package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAbsenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/organization/{organizationId}")
public class AbsenceController
{
    private final IAbsenceService _absenceService;

    public AbsenceController(IAbsenceService absenceService)
    {
        _absenceService = absenceService;
    }

    @GetMapping(value = "/absences")
    public String GetAbsences(@PathVariable(value="organizationId") Long organizationId,
                              Model model)
    {
        List<AbsenceDto> absenceDtos = _absenceService.GetAbsences(organizationId);

        model.addAttribute("absenceDtos", absenceDtos);
        model.addAttribute("bodyContent", "list-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/employee/{employeeId}/absences")
    public String GetEmployeeAbsences(@PathVariable(value="organizationId") Long organizationId,
                                      @PathVariable(value="employeeId") Long employeeId,
                                      Model model)
    {
        List<AbsenceDto> absenceDtos = _absenceService.GetUserAbsences(organizationId, employeeId);

        model.addAttribute("absenceDtos", absenceDtos);
        model.addAttribute("bodyContent", "list-employee-absence");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/absence/{absenceId}")
    public String GetAbsence(@PathVariable(value="organizationId") Long organizationId,
                             @PathVariable(value="absenceId") Long absenceId,
                             Model model)
    {
        AbsenceDto absenceDto = _absenceService.GetAbsence(organizationId, absenceId);

        model.addAttribute("absenceDto", absenceDto);
        model.addAttribute("bodyContent", "details-absence");
        HandleBaseAttributes(model);

        return "root";
    }
}
