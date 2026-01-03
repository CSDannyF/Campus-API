package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CampusService {
    List<Campus> allCampuses();
    Campus getCampusById(String campusId);
    Campus addCampuses(Campus campus);
    Campus updateCampus(String campusId, Campus campus);
    void deleteAll();
    void deleteCampus(String campusId);
    List<Room> getAllCampusRooms(String campusId);
    Room addRoomToCampus(String campusId, Room room);
}
