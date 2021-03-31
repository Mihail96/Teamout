package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/register")
public class RegisterController
{
    @GetMapping(value = "/organization")
    public String Login(Model model)
    {
        model.addAttribute("bodyContent", "register-organization");
        HandleBaseAttributes(model);

        return "root";
    }
}
