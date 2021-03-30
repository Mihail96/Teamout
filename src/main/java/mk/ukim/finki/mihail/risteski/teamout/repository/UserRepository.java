package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE u.Email = ?1")
    User GetUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.Email = ?1 AND u.PasswordHash = ?2")
    User GetUserByUsernameAndPassword(String email, String passwordHash);

    @Query("SELECT u " +
           "FROM User u " +
           "JOIN u.UserInOrganizations uio " +
           "JOIN uio.Organization o " +
           "WHERE u.Id = ?2 AND o.Id = ?1")
    User GetUser(Long organizationId, Long UserId);
}
