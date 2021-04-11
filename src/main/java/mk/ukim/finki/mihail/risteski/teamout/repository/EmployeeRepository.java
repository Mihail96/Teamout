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
           "JOIN e.UserInOrganization uio " +
           "JOIN uio.Organization o " +
           "WHERE o.Id = ?1")
    List<Employee> GetEmployeesInOrganization(Long organizationId);

    @Query("SELECT e FROM Employee e WHERE e.Id = ?1")
    Employee GetEmployeeById(Long employeeId);

    @Query("SELECT e FROM Employee e WHERE e.Id IN (?1)")
    List<Employee> GetEmployeeByIds(Long[] employeeIds);
}
