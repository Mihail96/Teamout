package mk.ukim.finki.mihail.risteski.teamout.config;

import mk.ukim.finki.mihail.risteski.teamout.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    private final UserService _userService;
    private final PasswordEncoder _passwordEncoder;

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder)
    {
        _userService = userService;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(email) || "".equals(password))
        {
            throw new BadCredentialsException("Invalid Credentials");
        }

        UserDetails userDetails = _userService.loadUserByUsername(email);
        if(userDetails == null || !_passwordEncoder.matches(password, userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid Credentials");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
