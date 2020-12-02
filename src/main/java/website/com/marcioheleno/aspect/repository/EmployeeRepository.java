package website.com.marcioheleno.aspect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.com.marcioheleno.aspect.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
