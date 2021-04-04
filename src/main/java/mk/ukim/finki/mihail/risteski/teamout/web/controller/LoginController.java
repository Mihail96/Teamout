package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/login")
public class LoginController
{
    @GetMapping
    public String Login(Model model)
    {
        model.addAttribute("bodyContent", "login");
        return "root";
    }
}
