package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.dto.OrganizationDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.request.CreateOrganizationRequest;
import mk.ukim.finki.mihail.risteski.teamout.model.request.OrganizationUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IOrganizationService
{
    Organization GetOrganization(Long id) throws NotFoundException;

    void CreateOrganization(CreateOrganizationRequest request, MultipartFile logoFile, MultipartFile userImageFile);

    OrganizationDto GetOrganizationProfile() throws NotFoundException;

    OrganizationDto UpdateOrganization(Long organizationId,
                                       OrganizationUpdateRequest organizationUpdateRequest,
                                       MultipartFile logoFile) throws NotFoundException;
}
