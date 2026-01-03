package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.repository.jpa.RoomJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImplementation implements RoomRepository {

    private RoomJpaRepository roomJpaRepository;

    @Autowired
    public RoomRepositoryImplementation(RoomJpaRepository roomJpaRepository) {
        this.roomJpaRepository = roomJpaRepository;
    }

    @Override
    public Room save(Room room) {
        return this.roomJpaRepository.save(room);
    }
}
