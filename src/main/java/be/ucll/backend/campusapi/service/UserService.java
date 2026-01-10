package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(long id);
    User addUser(User user);
    List<User> getUsersByPartOfName(String partOfName);
}
