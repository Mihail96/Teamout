package mk.ukim.finki.mihail.risteski.teamout.web.controller;

import mk.ukim.finki.mihail.risteski.teamout.model.auth.CurrentUser;
import mk.ukim.finki.mihail.risteski.teamout.model.auth.EmployeeUser;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.EmployeeDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.UserInOrganization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseController
{
    public static void HandleBaseAttributes(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated() &&
          authentication.getAuthorities().stream().noneMatch(x -> x.getAuthority().equals("ROLE_ANONYMOUS")))
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
                                               if(organization.getLogo() != null)
                                               {
                                                   currentUser.setLogoId(organization.getLogo().getId());
                                               }
                                           });
            currentUser.setUserId(user.getId());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setRoles(user.getAuthorities()
                                     .stream()
                                     .map(GrantedAuthority::getAuthority)
                                     .collect(Collectors.toSet()));
            Optional<Employee> optionalEmployee = user.getUserInOrganizations()
                    .stream()
                    .map(UserInOrganization::getEmployee)
                    .filter(Objects::nonNull)
                    .findFirst();

            if(optionalEmployee.isPresent())
            {
                Employee employee = optionalEmployee.get();
                EmployeeUser employeeUser = new EmployeeUser();

                employeeUser.setId(employee.getId());
                employeeUser.setHolidayDaysBalance(employee.getHolidayDaysBalance());
                employeeUser.setHolidayDaysUsed(employee.getHolidayDaysUsed());
                employeeUser.setSickleaveDaysBalance(employee.getSickleaveDaysBalance());
                employeeUser.setSickleaveDaysUsed(employee.getSickleaveDaysUsed());
                employeeUser.setExtraordinaryDaysBalance(employee.getExtraordinaryDaysBalance());
                employeeUser.setExtraordinaryDaysUsed(employee.getExtraordinaryDaysUsed());

                currentUser.setEmployeeUser(employeeUser);
            }

            model.addAttribute("currentUser", currentUser);
        }
        else
        {
            model.addAttribute("currentUser", null);
        }
    }
}
