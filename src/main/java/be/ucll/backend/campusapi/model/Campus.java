package be.ucll.backend.campusapi.model;

import be.ucll.backend.campusapi.error.RoomModelException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Campus {
    @Id
    private String campusName;

    @Column
    private String address;

    @Column
    private int numberOfParkingSpaces;

    @Column
    private int numberOfRooms;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public void updateCampus(Campus campus) {
        this.campusName = campus.campusName;
        this.address = campus.address;
        this.numberOfParkingSpaces = campus.numberOfParkingSpaces;
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new RoomModelException("Room cannot be null");
        }
        this.rooms.add(room);
        room.setCampus(this);
        setNumberOfRooms();
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String name) {
        this.campusName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfParkingSpaces() {
        return numberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms() {
        this.numberOfRooms = this.rooms.size();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
