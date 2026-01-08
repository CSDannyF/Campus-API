package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(long id);

    User addUser(User user);
}
