package be.ucll.backend.campusapi.controller;

import be.ucll.backend.campusapi.error.*;
import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.service.CampusService;
import be.ucll.backend.campusapi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campus")
public class CampusController {

    private CampusService campusService;
    private RoomService roomService;

    @Autowired
    public CampusController(CampusService campusService, RoomService roomService) {
        this.campusService = campusService;
        this.roomService = roomService;
    }


    @GetMapping
    public List<Campus> allCampuses() {
        return this.campusService.allCampuses();
    }

    /// Get campus by id wich is a string
    @GetMapping("/{campusId}")
    public Campus getCampusById(@PathVariable String campusId) {
        return this.campusService.getCampusById(campusId);
    }

    /// Post campus
    @PostMapping
    public Campus addCampus(@RequestBody Campus campus) {
        return this.campusService.addCampuses(campus);
    }

    /// Update campus, campusId/CampusName cannot be updated
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

    /// Get all rooms inside a campus with optional minimum seats filter
    /// Was not able to integrate availableFrom and availableUntil
    @GetMapping("/{campusId}/rooms")
    public List<Room> getCampusRooms(
            @PathVariable String campusId,
            @RequestParam(required = false, defaultValue = "0") int minNumberOfSeats) {
        return this.campusService.getCampusRooms(campusId, minNumberOfSeats);
    }

    /// Get room inside a campus by campusName and roomName
    @GetMapping("/{campusId}/rooms/{roomName}")
    public Room getRoomByName(@PathVariable String campusId, @PathVariable String roomName) {
        return this.roomService.getRoom(campusId, roomName);
    }

    /// Add room ro campus via campusID
    @PostMapping("/{campusId}/rooms")
    public Room addRoomToCampus(@PathVariable String campusId, @RequestBody Room room) {
        return this.campusService.addRoomToCampus(campusId, room);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RequiredFieldNameException.class})
    public Fieldmessage handleRequiredFieldException(RequiredFieldNameException e) {
        return new Fieldmessage("", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CampusException.class})
    public Fieldmessage handleCampusNameException(CampusException e) {
        return new Fieldmessage("campus", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoomException.class})
    public Fieldmessage handleRoomNameException(RoomException e) {
        return new Fieldmessage("name", e.getMessage());
    }
}
