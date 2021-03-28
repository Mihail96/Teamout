package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/organization")
public class DashboardController
{
    private final UserService _userService;

    public DashboardController(UserService userService)
    {
        _userService = userService;
    }

    @GetMapping(value = "/dashboard")
    public String Dashboard(Model model)
    {
        model.addAttribute("bodyContent", "dashboard");

        return "root";
    }
}
