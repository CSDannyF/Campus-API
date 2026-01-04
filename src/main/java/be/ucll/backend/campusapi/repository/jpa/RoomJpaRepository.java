package be.ucll.backend.campusapi.repository.jpa;

import be.ucll.backend.campusapi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByNameAndCampus_CampusName(String name, String campusCampusName);

    List<Room> findRoomByCapacityGreaterThanEqualAndCampus_CampusName(int capacityIsGreaterThan, String campusCampusName);
}
