package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.DraftUserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService extends UserDetailsService
{
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto GetUserProfile(Long userId);

    void CreateUser(DraftUserCreateRequest request, MultipartFile pictureFile);

    UserDto UpdateUser(Long userId, UserUpdateRequest request, MultipartFile pictureFile);

    DraftUserDto GetDraftUser(String activationCode) throws NotFoundException;
}
