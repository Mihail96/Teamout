package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController
{
    private final IAuthService _authService;

    public LoginController(IAuthService authService)
    {
        _authService = authService;
    }

    @GetMapping
    public String Login(Model model)
    {
        model.addAttribute("bodyContent", "login");
        return "root";
    }
}
