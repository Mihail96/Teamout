package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.dto.AbsenceDto;
import mk.ukim.finki.mihail.risteski.teamout.model.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>
{
    @Query("SELECT a " +
           "FROM Absence a " +
           "JOIN a.Employee e " +
           "JOIN e.UserInOrganization uio " +
           "JOIN uio.Organization o " +
           "WHERE o.Id = ?1")
    List<Absence> GetAbsencesInOrganization(Long organizationId);

    @Query("SELECT a " +
           "FROM Absence a " +
           "JOIN a.Employee e " +
           "JOIN e.UserInOrganization uio " +
           "JOIN uio.Organization o " +
           "WHERE o.Id = ?1 AND e.Id = ?2")
    List<Absence> GetAbsencesForEmployee(Long organizationId, Long employeeId);

    @Query("SELECT a " +
            "FROM Absence a " +
            "JOIN a.Employee e " +
            "JOIN e.UserInOrganization uio " +
            "JOIN uio.Organization o " +
            "WHERE o.Id = ?1 AND a.Id = ?2")
    Absence GetAbsence(Long organizationId, Long absenceId);

    @Query("SELECT a FROM Absence a WHERE a.Id = ?1")
    Absence GetAbsenceById(Long absenceId);
}
