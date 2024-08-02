package dogcare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import dogcare.entity.Dogcare;

public interface DogcareDao extends JpaRepository<Dogcare, Long> {
}
