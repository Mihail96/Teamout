package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.UserInOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInOrganizationRepository extends JpaRepository<UserInOrganization, Long>
{
}
