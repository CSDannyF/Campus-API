package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.*;
import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.repository.ReservationRepository;
import be.ucll.backend.campusapi.repository.RoomRepository;
import be.ucll.backend.campusapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImplementation implements ReservationService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    @Autowired
    public ReservationServiceImplementation(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    /// Get all reservations made by an user
    @Override
    public List<Reservation> getAllUserReservations(long userId) {
        User user = this.userRepository.getUserById(userId).orElseThrow(
                () -> new UserException("User does not exist")
        );
        return this.reservationRepository.findByUser(user);
    }

    /// Add reservation via given user
    @Override
    public Reservation addReservation(long userId, Reservation reservation) {

        // Check if start and end time are not null
        if (reservation.getEndTime() == null
                || reservation.getStartTime() == null) {
            throw new RequiredFieldNameException("Start time and end time cannot be null");
        }

        // Check if reservation start time is before end time
        if (reservation.getEndTime().isBefore(reservation.getStartTime())) {
            throw new ReservationTimeException("Reservation end time can not be before the start time");
        }

        // Check if reservation is before now
        if (reservation.getStartTime().isBefore(LocalDateTime.now())) {
            System.out.println("tijd uitvoering: " + LocalDateTime.now());
            throw new ReservationTimeException("Can not make a reservation in the past");
        }

        // Check if user exist
        User user = this.userRepository.getUserById(userId).orElseThrow(
                () -> new UserException("User does not exist")
        );

        user.addReservation(reservation);
        return this.reservationRepository.addReservation(reservation);
    }

    /// Get specific reservation by user
    @Override
    public Reservation getReservationByUser(long userId, long reservationId) {

        // Check if user exist
        User user = this.userRepository.getUserById(userId).orElseThrow(
                () -> new UserException("User does not exist")
        );

        // Check if reservation exist, if it does -> return reservation
        return this.reservationRepository.getReservation(user, reservationId).orElseThrow(
                () -> new ReservationException("Reservation does not exist")
        );
    }

    /// Add a room to a reservation made by an specific user
    @Override
    public Reservation addRoomToReservation(long userId, long reservationId, long roomId) {
        Reservation reservation = getReservationByUser(userId, reservationId);

        // Check if given room exist
        Room room = this.roomRepository.getRoomById(roomId).orElseThrow(
                () -> new RoomException("room doesn't exist")
        );

        // Check if room already exists in this reservation
        if (reservation.getRooms()
                .stream()
                .anyMatch(room1 -> room1.getRoomId() == roomId)) {
            throw new RoomException("Room already exists in this reservation");
        }

        // Check if reservation has overlap
        List<Reservation> reservations = this.reservationRepository.getAllReservations();
        if (checkIfRoomHasReservationOverlap(reservations, reservation, room)) {
            throw new RoomException("Room has a reservation overlap");
        }

        // Check if reservation belongs to user
        if (reservation.getUser().getUserId() != userId) {
            throw new ReservationException("Reservation does not belong to this user");
        }

        reservation.addRoomToReservation(room);

        return this.reservationRepository.saveRoomToReservation(reservation);
    }

    /// Check if room has a reservation overlap
    /// First check if the reservation had overlap with another reservation, if that's the case. Check if the room to book is already in de reservation.
    /// if true then the room reservation overlaps
    private boolean checkIfRoomHasReservationOverlap(List<Reservation> reservations, Reservation reservationToMake, Room roomToBook) {
        return reservations.stream()
                .filter(
                        reservation1 -> reservation1.getStartTime().isBefore(reservationToMake.getEndTime())
                                && reservation1.getEndTime().isAfter(reservationToMake.getStartTime()))
                .findFirst()
                .get()
                .getRooms()
                .stream().anyMatch(room1 -> room1 == roomToBook);
    }
}
