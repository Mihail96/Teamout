package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.request.CreateOrganizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/register")
public class RegisterController
{
    @GetMapping(value = "/organization")
    public String Login(Model model)
    {
        model.addAttribute("bodyContent", "register-organization");

        return "root";
    }
}
