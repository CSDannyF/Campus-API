package be.ucll.backend.campusapi.controller;

import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }
}
