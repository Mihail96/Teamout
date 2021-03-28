package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.repository.UserRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService
{
    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return _userRepository.GetUserByEmail(email);
    }
}
