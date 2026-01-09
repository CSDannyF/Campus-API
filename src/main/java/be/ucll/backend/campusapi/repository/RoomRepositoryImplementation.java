package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.error.RoomNameNeedsToBeUniqueException;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.repository.jpa.RoomJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryImplementation implements RoomRepository {

    private RoomJpaRepository roomJpaRepository;

    @Autowired
    public RoomRepositoryImplementation(RoomJpaRepository roomJpaRepository) {
        this.roomJpaRepository = roomJpaRepository;
    }

    @Override
    public Room save(Room room) {
        if (this.roomJpaRepository.findByNameAndCampus_CampusName(room.getName(), room.getCampus().getCampusName()).isPresent()) {
            throw new RoomNameNeedsToBeUniqueException();
        }
        return this.roomJpaRepository.save(room);
    }

    @Override
    public List<Room> getRoomsWithMinNumberOfSeats(String campusId, int minNumberOfSeats) {
        return List.of();
    }

    @Override
    public Optional<Room> getRoomByName(String campusId, String roomName) {
        return this.roomJpaRepository.findByNameAndCampus_CampusName(roomName, campusId);
    }

    @Override
    public List<Room> searchRooms(String campusId, int minNumberOfSeats) {
        return this.roomJpaRepository.findRoomByCapacityGreaterThanEqualAndCampus_CampusName(minNumberOfSeats, campusId);
    }

    @Override
    public Optional<Room> getRoomById(long roomId) {
        return this.roomJpaRepository.findById(roomId);
    }
}
