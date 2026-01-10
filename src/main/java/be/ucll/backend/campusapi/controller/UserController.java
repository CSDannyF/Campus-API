package be.ucll.backend.campusapi.controller;

import be.ucll.backend.campusapi.error.*;
import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.service.ReservationService;
import be.ucll.backend.campusapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ReservationService reservationService;

    @Autowired
    public UserController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    /// Get user by userId
    @GetMapping("/{user-id}")
    public User getUserById(@PathVariable(name = "user-id") long userId) {
        return this.userService.getUserById(userId);
    }

    /// Get user by part of name
    @GetMapping()
    public List<User> getUser(@RequestParam String partOfName) {
        return this.userService.getUsersByPartOfName(partOfName);
    }

    /// Add User
    @PostMapping
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    /// Get all reservations made by an user
    @GetMapping("/{user-id}/reservations")
    public List<Reservation> getUserReservations(@PathVariable(name = "user-id") long userId) {
        return this.reservationService.getAllUserReservations(userId);
    }

    /// Get a specific reservation made by a user via userId and reservationId
    @GetMapping("{user-id}/reservations/{reservation-id}")
    public Reservation getReservation(
            @PathVariable(name = "user-id") long userId,
            @PathVariable(name = "reservation-id") long reservationId) {
        return this.reservationService.getReservationByUser(userId, reservationId);
    }

    /// Add a reservation via a userId
    @PostMapping("/{user-id}/reservations")
    public Reservation addReservation(
            @PathVariable(name = "user-id") long userId,
            @RequestBody Reservation reservation) {
        return this.reservationService.addReservation(userId, reservation);
    }

    /// Add a room to a reservation if the given user is the owner of the reservation and the room is bookable
    @PutMapping("/{user-id}/reservations/{reservation-id}/rooms/{room-id}")
        public Reservation addRoomToReservation(
                @PathVariable(name = "user-id") long userId,
                @PathVariable(name = "reservation-id") long reservationId,
                @PathVariable(name = "room-id") long roomId) {
        return this.reservationService.addRoomToReservation(userId, reservationId, roomId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RequiredFieldNameException.class})
    public Fieldmessage handleRequiredFieldException(RequiredFieldNameException e) {
        return new Fieldmessage("", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserException.class})
    public Fieldmessage handeUserDoesntExistException(UserException e) {
        return new Fieldmessage("user", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ReservationException.class})
    public Fieldmessage handleReservationDoesntExistException(ReservationException e) {
        return new Fieldmessage("reservation", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoomException.class})
    public Fieldmessage handeRoomNameDoesntExistException(RoomException e) {
        return new Fieldmessage("name", e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ReservationTimeException.class})
    public Fieldmessage handleReservationTimeException(ReservationTimeException e) {
        return new Fieldmessage("StartTime/EndTime", e.getMessage());
    }
}
