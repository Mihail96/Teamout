package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.DraftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftUserRepository extends JpaRepository<DraftUser, Long>
{
    @Query("SELECT du " +
           "FROM DraftUser du " +
           "WHERE du.ActivationCode = ?1")
    DraftUser GetDraftUserByActivationCode(String activationCOde);
}
