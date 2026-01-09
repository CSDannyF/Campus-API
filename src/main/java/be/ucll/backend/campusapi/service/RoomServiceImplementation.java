package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.RoomDoesntExistException;
import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImplementation implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImplementation(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addRoom(Room room) {
        return this.roomRepository.save(room);
    }

    @Override
    public Room getRoom(String campusId, String roomName) {
        return this.roomRepository.getRoomByName(campusId, roomName).orElseThrow(
                RoomDoesntExistException::new
        );
    }

    @Override
    public List<Room> searchRooms(Campus campus, int minNumberOfSeats) {
        return  campus.getRooms().stream().filter(
                        r -> r.getCapacity() >= minNumberOfSeats)
                .toList();    }
}
