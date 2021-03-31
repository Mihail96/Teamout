package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.request.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService extends UserDetailsService
{
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto GetUserProfile(Long organizationId, Long userId);

    UserDto UpdateUser(Long organizationId, Long userId, UserUpdateRequest request, MultipartFile pictureFile);
}
