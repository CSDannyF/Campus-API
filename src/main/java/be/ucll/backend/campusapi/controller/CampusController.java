package be.ucll.backend.campusapi.controller;

import be.ucll.backend.campusapi.error.*;
import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campus")
public class CampusController {

    private CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @GetMapping
    public List<Campus> allCampuses() {
        return this.campusService.allCampuses();
    }

    @GetMapping("/{campusId}")
    public Campus getCampusById(@PathVariable String campusId) {
        return this.campusService.getCampusById(campusId);
    }

    @PostMapping
    public Campus addCampus(@RequestBody Campus campus) {
        return this.campusService.addCampuses(campus);
    }

    @PutMapping("/{campusId}")
    public Campus updateCampus(@PathVariable String campusId, @RequestBody Campus campus) {
        return this.campusService.updateCampus(campusId, campus);
    }

    @DeleteMapping
    public void deleteAllCampuses() {
        this.campusService.deleteAll();
    }

    @DeleteMapping("/{campusId}")
    public void deleteCampus(@PathVariable String campusId) {
        this.campusService.deleteCampus(campusId);
    }

    @GetMapping("/{campusId}/rooms")
    public List<Room> getAllCampusRooms(@PathVariable String campusId) {
        return this.campusService.getAllCampusRooms(campusId);
    }

    @PostMapping("/{campusId}/rooms")
    public Room addRoomToCampus(@PathVariable String campusId, @RequestBody Room room) {
        return this.campusService.addRoomToCampus(campusId, room);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RequiredFieldNameException.class})
    public Fieldmessage handleRequiredFieldException() {
        return new Fieldmessage("", "provide all fields");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusNameNeedsToBeUniqueException.class})
    public Fieldmessage handleCampusNameNeedsToBeUniqueException() {
        return new Fieldmessage("campusName", "campus name needs to be unique");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusNameDoesntExistException.class})
    public Fieldmessage handleCampusNameDoesntExist() {
        return new Fieldmessage("campus", "campus name doesn't exist");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({CampusNameCannotBeChangedException.class})
    public Fieldmessage handleCampusNameCannotBeChangedException() {
        return new Fieldmessage("campusName", "campus name cannot be changed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoomNameNeedsToBeUniqueException.class})
    public Fieldmessage handleRoomNameNeedsToBeUniqueException() {
        return new Fieldmessage("name", "room name needs to be unique");
    }
}
