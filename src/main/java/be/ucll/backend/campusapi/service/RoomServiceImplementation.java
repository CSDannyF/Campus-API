package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
