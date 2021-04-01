package mk.ukim.finki.mihail.risteski.teamout.repository;

import mk.ukim.finki.mihail.risteski.teamout.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    @Query("SELECT e " +
           "FROM Employee e " +
           "JOIN UserInOrganization.Employee uio " +
           "JOIN Organization.UsersInOrganization o " +
           "WHERE o.Id = ?1")
    List<Employee> GetEmployeesInOrganization(Long organizationId);
}
