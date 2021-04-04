package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.UserUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IOrganizationService;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping
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

    @GetMapping(value = "/user/{userId}/profile")
    public String GetUserProfile(@PathVariable(value="userId")Long userId,
                                 Model model)
    {
        UserDto userDto = _userService.GetUserProfile(userId);

        model.addAttribute("userDto", userDto);
        model.addAttribute("bodyContent", "profile-user");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "user/create")
    public String CreateUser(@RequestParam("picture") MultipartFile pictureFile,
                             DraftUserCreateRequest request,
                             Model model)
    {
        _userService.CreateUser(request, pictureFile);

        model.addAttribute("bodyContent", "login");
        HandleBaseAttributes(model);

        return "redirect:/login";
    }

    @PostMapping(value = "/user/{userId}/update")
    public String UpdateUser(@PathVariable(value="userId")Long userId,
                             @RequestParam("picture") MultipartFile pictureFile,
                             UserUpdateRequest request,
                             Model model)
    {
        UserDto userDto = _userService.UpdateUser(userId, request, pictureFile);

        model.addAttribute("userDto", userDto);
        model.addAttribute("bodyContent", "profile-user");
        HandleBaseAttributes(model);

        return "root";
    }
}
