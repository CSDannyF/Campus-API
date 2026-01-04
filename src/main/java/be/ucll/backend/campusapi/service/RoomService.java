package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    Room addRoom(Room room);
    Room getRoom(String campusId, String roomName);
    List<Room> searchRooms(Campus campus, int minNumberOfSeats);
}
