package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Campus;

import java.util.List;
import java.util.Optional;

public interface CampusRepository {
    List<Campus> getAll();
    Optional<Campus> getById(String campusId);
    Campus addCampus(Campus campus);
    Campus updateCampus(Campus campusToUpdate);
    void deleteAllCampuses();
    void deleteCampus(String campusById);
}
