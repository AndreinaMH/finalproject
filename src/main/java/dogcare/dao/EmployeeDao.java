package dogcare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import dogcare.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}


