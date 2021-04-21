package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Image;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.UserInOrganization;
import mk.ukim.finki.mihail.risteski.teamout.repository.ImageRepository;
import mk.ukim.finki.mihail.risteski.teamout.repository.OrganizationRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
@RequestMapping(value = "/organization")
public class ImageController
{
    private final ImageRepository _imageRepository;
    private final OrganizationRepository _organizationRepository;

    public ImageController(ImageRepository imageRepository,
                           OrganizationRepository organizationRepository)
    {
        _imageRepository = imageRepository;
        _organizationRepository = organizationRepository;
    }

    @GetMapping("/{organizationid}/image/{id}")
    public void GetImage(@PathVariable Long organizationid,
                         @PathVariable Long id,
                         HttpServletResponse response) throws NotFoundException, IOException {
        response.setContentType("image/jpeg");

        Optional<Image> image = _imageRepository.findById(id);

        if(image.isEmpty())
        {
            throw new NotFoundException("Could not find image with id " + id.toString());
        }

        InputStream is = new ByteArrayInputStream(image.get().getContent());
        IOUtils.copy(is, response.getOutputStream());
    }

    @PostMapping("/{organizationid}/image/{id}")
    public void DeleteImage(@PathVariable Long organizationid,
                            @PathVariable Long id,
                            HttpServletResponse response) throws NotFoundException, IOException {
        response.setContentType("image/jpeg");

        Optional<Image> image = _imageRepository.findById(id);

        if(image.isEmpty())
        {
            throw new NotFoundException("Could not find image with id " + id.toString());
        }

        Organization organization = _organizationRepository.GetOrganizationById(organizationid);
        if(organization.getLogo() != null && organization.getLogo().getId().equals(id))
        {
            organization.setLogo(null);
        }

        organization.getUsersInOrganization().forEach(x ->
        {
            User user = x.getUser();
            if(user.getPicture() != null && user.getPicture().getId().equals(id))
            {
                user.setPicture(null);
            }
        });

        _organizationRepository.save(organization);
        _imageRepository.delete(image.get());
        _imageRepository.flush();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated() &&
                authentication.getAuthorities().stream().noneMatch(x -> x.getAuthority().equals("ROLE_ANONYMOUS")))
        {
            User user = (User)authentication.getPrincipal();
            if(user.getPicture() != null && user.getPicture().getId().equals(id))
            {
                user.setPicture(null);
            }

            Optional<Organization> optionalOrganization = user.getUserInOrganizations()
                                                              .stream()
                                                              .map(UserInOrganization::getOrganization)
                                                              .findFirst();
            if(optionalOrganization.isEmpty())
            {
                throw new NotFoundException("Could not find organization with id " + organizationid.toString());
            }

            Organization currentOrganization = optionalOrganization.get();
            if(currentOrganization.getLogo() != null && currentOrganization.getLogo().getId().equals(id))
            {
                currentOrganization.setLogo(null);
            }
        }
    }
}
