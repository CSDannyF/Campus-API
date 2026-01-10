package be.ucll.backend.campusapi.model;

import be.ucll.backend.campusapi.error.RoomException;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    /*
    https://manishkrb.medium.com/jsonproperty-access-jsonproperty-access-read-only-8c5487af6100
    JsonProperty.acces.READ_ONLY zorgt ervoor dat dit veld enkel leesbaar is en komt zo ook niet in de swagger ui
     */
    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int numberOfRooms;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Room> rooms = new ArrayList<>();

    public void updateCampus(Campus campus) {
        this.campusName = campus.campusName;
        this.address = campus.address;
        this.numberOfParkingSpaces = campus.numberOfParkingSpaces;
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new RoomException("Room cannot be null");
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
