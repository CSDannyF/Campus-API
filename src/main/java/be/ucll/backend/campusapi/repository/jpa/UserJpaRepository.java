package be.ucll.backend.campusapi.repository.jpa;

import be.ucll.backend.campusapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
