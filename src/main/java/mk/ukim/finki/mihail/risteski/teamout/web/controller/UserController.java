package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IOrganizationService;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/organization/{organizationId}/user")
public class UserController
{
    public final IOrganizationService _organizationService;
    public final IUserService _userService;

    public UserController(IOrganizationService organizationService,
                          IUserService userService)
    {
        _organizationService = organizationService;
        _userService = userService;
    }

    @GetMapping(value = "/{userId}/profile")
    public String GetUserProfile(@PathVariable(value="organizationId")Long organizationId,
                                 @PathVariable(value="userId")Long userId,
                                 Model model)
    {
        UserDto userDto = _userService.GetUserProfile(organizationId, userId);

        model.addAttribute("userDto", userDto);
        model.addAttribute("bodyContent", "profile-user");
        HandleBaseAttributes(model);

        return "root";
    }
}
