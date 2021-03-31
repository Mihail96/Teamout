package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IOrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static mk.ukim.finki.mihail.risteski.teamout.web.controller.BaseController.HandleBaseAttributes;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController
{
    public final IOrganizationService _organizationService;

    public OrganizationController(IOrganizationService organizationService)
    {
        _organizationService = organizationService;
    }

    @GetMapping(value = "/{organizationId}/profile")
    public String GetOrganizationProfile(@PathVariable(value="organizationId")Long id,
                                         Model model) throws NotFoundException
    {
        OrganizationDto organizationDto = _organizationService.GetOrganizationProfile(id);

        model.addAttribute("organizationDto", organizationDto);
        model.addAttribute("bodyContent", "profile-organization");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/{organizationId}/update")
    public String UpdateOrganization(@PathVariable(value="organizationId")Long organizationId,
                                     @RequestParam("logo") MultipartFile logoFile,
                                     OrganizationUpdateRequest organizationUpdateRequest,
                                     Model model) throws NotFoundException
    {
        OrganizationDto organizationDto = _organizationService.UpdateOrganization(organizationId,
                                                                                  organizationUpdateRequest,
                                                                                  logoFile);

        model.addAttribute("organizationDto", organizationDto);
        model.addAttribute("bodyContent", "profile-organization");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/create")
    public String CreateOrganization(OrganizationCreateRequest organizationRequest,
                                     @RequestParam("userImage") MultipartFile userImageFile,
                                     @RequestParam("logo") MultipartFile logoFile,
                                     Model model)
    {

        _organizationService.CreateOrganization(organizationRequest, logoFile, userImageFile);

        model.addAttribute("bodyContent", "login");
        HandleBaseAttributes(model);

        return "redirect:/login";
    }
}
