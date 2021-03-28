package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.AbsenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceStatusRepository extends JpaRepository<AbsenceStatus, Long>
{

}