package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.auth.CurrentUser;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.UserInOrganization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.stream.Collectors;

public class BaseController
{
    public static void HandleBaseAttributes(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated() &&
          !authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ANONYMOUS")))
        {
            User user = (User)authentication.getPrincipal();
            Optional<Organization> optionalOrganization = user.getUserInOrganizations()
                                                              .stream()
                                                              .map(UserInOrganization::getOrganization)
                                                              .findFirst();
            CurrentUser currentUser = new CurrentUser();
            optionalOrganization.ifPresent(organization ->
                                           {
                                               currentUser.setOrganizationId(organization.getId());
                                               currentUser.setOrganizationName(organization.getName());
                                               currentUser.setLogoId(organization.getLogo().getId());
                                           });
            currentUser.setUserId(user.getId());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setRoles(user.getAuthorities()
                                     .stream()
                                     .map(GrantedAuthority::getAuthority)
                                     .collect(Collectors.toSet()));

            model.addAttribute("currentUser", currentUser);
        }
        else
        {
            model.addAttribute("currentUser", null);
        }
    }
}
