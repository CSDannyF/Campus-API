package be.ucll.backend.campusapi.repository.jpa;

import be.ucll.backend.campusapi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}
