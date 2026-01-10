package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.RequiredFieldNameException;
import be.ucll.backend.campusapi.error.UserException;
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

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /// Get user by Id
    @Override
    public User getUserById(long id) {
        return this.userRepository.getUserById(id).orElseThrow(
                () -> new UserException("User does not exist")
        );
    }

    /// Add an user
    @Override
    public User addUser(User user) {
        if (user.getName().isEmpty()
                || user.getDateOfBirth() == null) {
            throw new RequiredFieldNameException("Provide all fields");
        }
        return this.userRepository.addUser(user);
    }

    /// Get an user by a part of it's name, return type is a list because it can be that there are more users with that part of the name
    @Override
    public List<User> getUsersByPartOfName(String partOfName) {
        return this.userRepository.getAllUsers().stream()
                .filter(user -> user.getName().contains(partOfName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
