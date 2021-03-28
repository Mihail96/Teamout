package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.enumeration.RoleEnum;
import mk.ukim.finki.mihail.risteski.teamout.model.request.CreateOrganizationRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.OrganizationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController
{
    public final OrganizationService _organizationService;

    public OrganizationController(OrganizationService organizationService)
    {
        _organizationService = organizationService;
    }

    @GetMapping(value = "/profile")
    public String GetCompanyProfile(Model model) throws NotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User)authentication.getPrincipal();

        Optional<Organization> optionalOrganization =
                currentUser.getUserInOrganizations().stream().filter(x -> x.getRole().getName().equals(RoleEnum.Owner.getName()))
                                                             .map(x -> x.getOrganization()).findFirst();

        if(optionalOrganization.isEmpty())
        {
            throw new NotFoundException("Couldn't find organization");
        }

        model.addAttribute("Organization", optionalOrganization.get());
        model.addAttribute("bodyContent", "profile-organization");
        return "root";
    }

    @PostMapping(value = "/create")
    public String CreateOrganization(@ModelAttribute(value = "organizationRequest") CreateOrganizationRequest organizationRequest,
                                     @RequestParam("userImage") MultipartFile userImageFile,
                                     @RequestParam("logo") MultipartFile logoFile,
                                     Model model)
    {
        if(userImageFile != null && userImageFile.getOriginalFilename() != null)
        {
            organizationRequest.PictureName = StringUtils.cleanPath(userImageFile.getOriginalFilename());
            try
            {
                organizationRequest.PictureContent = userImageFile.getBytes();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if(logoFile != null && logoFile.getOriginalFilename() != null)
        {
            organizationRequest.LogoName = StringUtils.cleanPath(logoFile.getOriginalFilename());
            try
            {
                organizationRequest.LogoContent = logoFile.getBytes();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        _organizationService.CreateOrganization(organizationRequest);

        model.addAttribute("bodyContent", "login");

        return "redirect:/login";
    }
}
