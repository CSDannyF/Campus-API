package be.ucll.backend.campusapi.model;

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

    @OneToMany(mappedBy = "campus")
    private List<Classroom> classrooms = new ArrayList<>();

    public void updateCampus(Campus campus) {
        this.campusName = campus.campusName;
        this.address = campus.address;
        this.numberOfParkingSpaces = campus.numberOfParkingSpaces;
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
        this.numberOfRooms = this.classrooms.size();
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
