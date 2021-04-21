package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u " +
           "FROM User u " +
           "JOIN u.UserInOrganizations uio " +
           "JOIN uio.Organization o " +
           "WHERE o.Id = ?1 AND uio.Role.Name = ?2")
    List<User> GetAdminsInOrganization(Long organizationId, String role);
}
