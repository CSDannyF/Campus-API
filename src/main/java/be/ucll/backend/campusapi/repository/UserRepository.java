package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> getUserById(long id);
    User addUser(User user);
    List<User> getAllUsers( );
}
