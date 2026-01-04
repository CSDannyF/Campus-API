package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.repository.jpa.CampusJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CampusRepositoryImplementation implements CampusRepository {

    private CampusJpaRepository campusJpaRepository;

    @Autowired
    public CampusRepositoryImplementation(CampusJpaRepository campusJpaRepository) {
        this.campusJpaRepository = campusJpaRepository;
    }

    @Override
    public List<Campus> getAll() {
        return this.campusJpaRepository.findAll();
    }

    @Override
    public Optional<Campus> getById(String campusId) {
        return this.campusJpaRepository.findById(campusId);
    }

    @Override
    public Campus addCampus(Campus campus) {
        return this.campusJpaRepository.save(campus);
    }

    @Override
    public Campus updateCampus(Campus campusToUpdate) {
        return this.campusJpaRepository.save(campusToUpdate);
    }

    @Override
    public void deleteAllCampuses() {
        this.campusJpaRepository.deleteAll();
    }

    @Override
    public void deleteCampus(String campusById) {
        this.campusJpaRepository.deleteById(campusById);
    }
}
