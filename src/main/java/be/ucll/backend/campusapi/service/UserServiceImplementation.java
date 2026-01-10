package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.UserDoesntExistException;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private ReservationService reservationService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, ReservationService reservationService) {
        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.getUserById(id).orElseThrow(
                UserDoesntExistException::new
        );
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }

    @Override
    public List<User> getUsersByPartOfName(String partOfName) {
        return this.userRepository.getAllUsers().stream()
                .filter(user -> user.getName().contains(partOfName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
