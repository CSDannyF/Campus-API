package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository {
    Room save(Room room);
    List<Room> getRoomsWithMinNumberOfSeats(String campusId, int minNumberOfSeats);
    Optional<Room> getRoomByName(String campusId, String roomName);
    List<Room> searchRooms(String campusId, int minNumberOfSeats);
}
