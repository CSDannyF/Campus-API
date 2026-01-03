package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.CampusNameCannotBeChanged;
import be.ucll.backend.campusapi.error.CampusNameDoesntExists;
import be.ucll.backend.campusapi.error.CampusNameNeedsToBeUnique;
import be.ucll.backend.campusapi.error.RequiredFieldNameException;
import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusServiceImplementation implements CampusService {

    private CampusRepository campusRepository;

    @Autowired
    public CampusServiceImplementation(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    public List<Campus> allCampuses() {
        return this.campusRepository.getAll();
    }

    @Override
    public Campus getCampusById(String campusId) {
        return this.campusRepository.getById(campusId).orElseThrow(
                CampusNameDoesntExists::new
        );
    }

    @Override
    public Campus addCampuses(Campus campus) {
        if (allCampuses().stream().anyMatch(campus1 -> campus1.getCampusName().equals(campus.getCampusName()))) {
            throw new CampusNameNeedsToBeUnique();
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
            throw new CampusNameCannotBeChanged();
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
}
