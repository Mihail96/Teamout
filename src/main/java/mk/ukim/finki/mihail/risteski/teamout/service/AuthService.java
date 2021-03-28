package mk.ukim.finki.mihail.risteski.teamout.service;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.repository.UserRepository;
import mk.ukim.finki.mihail.risteski.teamout.service.contract.IAuthService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
public class AuthService implements IAuthService
{
    private final UserRepository _userRepository;

    public AuthService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public User Login(String email, String password) throws CredentialException
    {
        if (email==null || email.isEmpty() || password==null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        User user = _userRepository.GetUserByUsernameAndPassword(email, password);
        if(user == null)
        {
            throw new CredentialException();
        }

        return user;
    }
}
