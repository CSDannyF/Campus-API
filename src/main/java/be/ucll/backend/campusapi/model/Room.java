package be.ucll.backend.campusapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long roomId;

    @Column(unique = true)
    private String name;

    @Column
    private String type;

    @Column
    private int capacity;

    @Column
    private String firstName;

    @Column
    private String LastName;

    @Column
    private String floor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "campusName")
    private Campus campus;

    @ManyToMany(mappedBy = "rooms")
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long id) {
        this.roomId = id;
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }
    //TODO zorgen dat deze setter niet in mijn POST json te zien is
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public void setReservationToRoom(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
