package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.Room;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    Room addRoom(Room room);
}
