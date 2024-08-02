package dogcare.dao;

import org.springframework.data.jpa.repository.JpaRepository; 
import dogcare.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
