package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Long, Absence>
{
    @Query("SELECT a " +
           "FROM Absence a " +
           "JOIN a.Employee e " +
           "JOIN e.UserInOrganization uio " +
           "JOIN uio.Organization o " +
           "WHERE o.Id = ?1")
    List<Absence> GetAbsencesInOrganization(Long organizationId);
}
