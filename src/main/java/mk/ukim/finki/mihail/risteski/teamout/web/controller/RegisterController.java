package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.DraftUserDto;
import mk.ukim.finki.mihail.risteski.teamout.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/register")
public class RegisterController
{
    private final UserService _userService;

    public RegisterController(UserService userService)
    {
        _userService = userService;
    }

    @GetMapping(value = "/organization")
    public String CreateOrganization(Model model)
    {
        model.addAttribute("bodyContent", "register-organization");
        return "root";
    }

    @GetMapping(value = "/user/{activationCode}")
    public String GetCreateDraftUser(@PathVariable(value="activationCode") String activationCode,
                                     Model model) throws NotFoundException
    {
        DraftUserDto draftUserDto = _userService.GetDraftUser(activationCode);

        model.addAttribute("bodyContent", "register-user");
        model.addAttribute("draftUserDto", draftUserDto);
        return "root";
    }
}
