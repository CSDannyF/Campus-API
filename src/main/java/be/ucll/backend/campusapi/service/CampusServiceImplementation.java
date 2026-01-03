package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.CampusNameCannotBeChangedException;
import be.ucll.backend.campusapi.error.CampusNameDoesntExistException;
import be.ucll.backend.campusapi.error.CampusNameNeedsToBeUniqueException;
import be.ucll.backend.campusapi.error.RequiredFieldNameException;
import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusServiceImplementation implements CampusService {

    private CampusRepository campusRepository;
    private RoomService roomService;

    @Autowired
    public CampusServiceImplementation(CampusRepository campusRepository, RoomService roomService) {
        this.campusRepository = campusRepository;
        this.roomService = roomService;
    }

    @Override
    public List<Campus> allCampuses() {
        return this.campusRepository.getAll();
    }

    @Override
    public Campus getCampusById(String campusId) {
        return this.campusRepository.getById(campusId).orElseThrow(
                CampusNameDoesntExistException::new
        );
    }

    @Override
    public Campus addCampuses(Campus campus) {
        if (allCampuses().stream().anyMatch(campus1 -> campus1.getCampusName().equals(campus.getCampusName()))) {
            throw new CampusNameNeedsToBeUniqueException();
        }

        if (campus.getCampusName().equals("")
        || campus.getAddress().equals("")
        || campus.getNumberOfParkingSpaces() == 0) {
            throw new RequiredFieldNameException();
        }
        return this.campusRepository.addCampus(campus);
    }

    @Override
    public Campus updateCampus(String campusId, Campus campus) {
        Campus campusToUpdate = getCampusById(campusId);

        if(!campusId.equals(campus.getCampusName())) {
            throw new CampusNameCannotBeChangedException();
        }

        campusToUpdate.updateCampus(campus);
        return campusRepository.updateCampus(campusToUpdate);
    }

    @Override
    public void deleteAll() {
        this.campusRepository.deleteAllCampuses();
    }

    @Override
    public void deleteCampus(String campusId) {
        getCampusById(campusId);
        this.campusRepository.deleteCampus(campusId);
    }

    @Override
    public List<Room> getAllCampusRooms(String campusId) {
         Campus campus = getCampusById(campusId);
         return campus.getRooms();
    }

    @Override
    public Room addRoomToCampus(String campusId, Room room) {
        Campus campus = getCampusById(campusId);

        campus.addRoom(room);
        //Campus updatedCampus = this.campusRepository.addCampus(campus);
        return this.roomService.addRoom(room);

    }
}
