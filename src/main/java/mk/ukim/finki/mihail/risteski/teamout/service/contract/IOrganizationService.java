package mk.ukim.finki.mihail.risteski.teamout.service.contract;

import javassist.NotFoundException;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Organization;
import mk.ukim.finki.mihail.risteski.teamout.model.request.CreateOrganizationRequest;

public interface IOrganizationService
{
    Organization GetOrganization(Long id) throws NotFoundException;

    void CreateOrganization(CreateOrganizationRequest request);
}
