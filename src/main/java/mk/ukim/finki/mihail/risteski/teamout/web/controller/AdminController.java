package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/organization/{organizationId}/admin")
public class AdminController
{
    private final IAdminService _adminService;

    public AdminController(IAdminService adminService)
    {
        _adminService = adminService;
    }

    @GetMapping(value = "/list")
    public String GetAdminList(@PathVariable(value="organizationId") Long organizationId,
                               Model model)
    {
        List<UserDto> adminDtos = _adminService.GetAdminList(organizationId);

        model.addAttribute("adminDtos", adminDtos);
        model.addAttribute("bodyContent", "list-admin");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/invite")
    public String GetInviteAdmin(@PathVariable(value="organizationId") Long organizationId,
                                    Model model)
    {
        model.addAttribute("bodyContent", "invite-admin");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/invite")
    public String InviteEmployee(@PathVariable(value="organizationId") Long organizationId,
                                 DraftUserCreateRequest draftUserCreateRequest,
                                 Model model)
    {
        List<UserDto> adminDtos = _adminService.InviteAdmin(organizationId, draftUserCreateRequest);

        model.addAttribute("adminDtos", adminDtos);
        model.addAttribute("bodyContent", "list-admin");
        HandleBaseAttributes(model);

        return "root";
    }
}
