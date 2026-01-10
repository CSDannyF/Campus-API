package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImplementation implements UserRepository {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryImplementation(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return this.userJpaRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        return this.userJpaRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userJpaRepository.findAll();
    }
}
