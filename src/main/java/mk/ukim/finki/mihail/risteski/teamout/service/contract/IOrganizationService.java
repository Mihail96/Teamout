package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationCreateRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IOrganizationService
{
    void CreateOrganization(OrganizationCreateRequest request, MultipartFile logoFile, MultipartFile userImageFile);

    OrganizationDto GetOrganizationProfile(Long organizationId) throws NotFoundException;

    OrganizationDto UpdateOrganization(Long organizationId,
                                       OrganizationUpdateRequest organizationUpdateRequest,
                                       MultipartFile logoFile) throws NotFoundException;
}
