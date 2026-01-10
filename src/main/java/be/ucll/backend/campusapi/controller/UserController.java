package be.ucll.backend.campusapi.controller;

import be.ucll.backend.campusapi.error.ReservationDoesntExistException;
import be.ucll.backend.campusapi.error.ReservationTimeException;
import be.ucll.backend.campusapi.error.RoomDoesntExistException;
import be.ucll.backend.campusapi.error.UserDoesntExistException;
import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.service.ReservationService;
import be.ucll.backend.campusapi.service.UserService;
import jakarta.xml.bind.annotation.XmlRootElement;
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

    @GetMapping("/{user-id}")
    public User getUserById(@PathVariable(name = "user-id") long userId) {
        return this.userService.getUserById(userId);
    }

    @GetMapping()
    public List<User> getUser(@RequestParam String partOfName) {
        return this.userService.getUsersByPartOfName(partOfName);
    }


    @PostMapping
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @GetMapping("/{user-id}/reservations")
    public List<Reservation> getUserReservations(@PathVariable(name = "user-id") long userId) {
        return this.reservationService.getAllUserReservations(userId);
    }

    @GetMapping("{user-id}/reservations/{reservation-id}")
    public Reservation getReservation(
            @PathVariable(name = "user-id") long userId,
            @PathVariable(name = "reservation-id") long reservationId) {
        return this.reservationService.getReservationByUser(userId, reservationId);
    }

    @PostMapping("/{user-id}/reservations")
    public Reservation addReservation(
            @PathVariable(name = "user-id") long userId,
            @RequestBody Reservation reservation) {
        return this.reservationService.addReservation(userId, reservation);
    }

    @PutMapping("/{user-id}/reservations/{reservation-id}/rooms/{room-id}")
        public Reservation addRoomToReservation(
                @PathVariable(name = "user-id") long userId,
                @PathVariable(name = "reservation-id") long reservationId,
                @PathVariable(name = "room-id") long roomId) {
        return this.reservationService.addRoomToReservation(userId, reservationId, roomId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserDoesntExistException.class})
    public Fieldmessage handeUserDoesntExistException() {
        return new Fieldmessage("user", "user doesn't exist");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ReservationDoesntExistException.class})
    public Fieldmessage handleReservationDoesntExistException() {
        return new Fieldmessage("reservation", "reservation doesn't exist");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoomDoesntExistException.class})
    public Fieldmessage handeRoomNameDoesntExistException() {
        return new Fieldmessage("name", "room doesn't exist");
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ReservationTimeException.class})
    public Fieldmessage handleReservationTimeException(ReservationTimeException e) {
        return new Fieldmessage("StartTime/EndTime", "error on startTime and/or EndTime fields");
    }
}
