package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository {
    Room save(Room room);
}
