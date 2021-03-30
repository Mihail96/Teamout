package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.CreateOrganizationRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationUpdateRequest;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IOrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

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

    @GetMapping(value = "/profile")
    public String GetOrganizationProfile(Model model) throws NotFoundException
    {
        OrganizationDto organizationDto = _organizationService.GetOrganizationProfile();

        model.addAttribute("organizationDto", organizationDto);
        model.addAttribute("bodyContent", "profile-organization");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/update/organizationId")
    public String UpdateOrganization(OrganizationUpdateRequest organizationUpdateRequest,
                                     @PathParam("organizationId") Long organizationId,
                                     Model model) throws NotFoundException
    {
        OrganizationDto organizationDto = _organizationService.UpdateOrganization(organizationId, organizationUpdateRequest);

        model.addAttribute("organizationDto", organizationDto);
        model.addAttribute("bodyContent", "profile-organization");
        HandleBaseAttributes(model);

        return "root";
    }

    @PostMapping(value = "/create")
    public String CreateOrganization(CreateOrganizationRequest organizationRequest,
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
