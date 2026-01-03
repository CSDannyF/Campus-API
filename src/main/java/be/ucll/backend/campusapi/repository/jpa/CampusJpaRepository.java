package be.ucll.backend.campusapi.repository.jpa;

import be.ucll.backend.campusapi.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusJpaRepository extends JpaRepository<Campus, String> {
}
