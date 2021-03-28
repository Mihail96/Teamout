package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService
{
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
