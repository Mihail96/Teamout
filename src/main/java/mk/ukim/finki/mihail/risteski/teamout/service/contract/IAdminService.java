package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.UserDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.DraftUserCreateRequest;

import java.util.List;

public interface IAdminService
{
    List<UserDto> GetAdminList(Long organizationId);

    List<UserDto> InviteAdmin(Long organizationId, DraftUserCreateRequest draftUserCreateRequest);
}
