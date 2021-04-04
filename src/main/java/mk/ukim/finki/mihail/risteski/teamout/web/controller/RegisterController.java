package mk.ukim.finki.mihail.risteski.teamout.web.controller;

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
    @GetMapping(value = "/organization")
    public String CreateOrganization(Model model)
    {
        model.addAttribute("bodyContent", "register-organization");
        HandleBaseAttributes(model);

        return "root";
    }

    @GetMapping(value = "/employee/{activationCode}")
    public String CreateEmployeeUser(@PathVariable(value="activationCode") String activationCode,
                             Model model)
    {
        model.addAttribute("bodyContent", "register-employee");
        HandleBaseAttributes(model);

        return "root";
    }
}
