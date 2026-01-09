package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.ReservationDoesntExistException;
import be.ucll.backend.campusapi.error.RoomDoesntExistException;
import be.ucll.backend.campusapi.error.UserDoesntExistException;
import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.repository.ReservationRepository;
import be.ucll.backend.campusapi.repository.RoomRepository;
import be.ucll.backend.campusapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Reservation> getAllUserReservations(long userId) {
        User user = this.userRepository.getUserById(userId).orElseThrow(
                UserDoesntExistException::new
        );
        return this.reservationRepository.findByUser(user);
    }

    @Override
    public Reservation addReservation(long userId, Reservation reservation) {
        User user = this.userRepository.getUserById(userId).orElseThrow(
                UserDoesntExistException::new
        );

        user.addReservation(reservation);
        return this.reservationRepository.addReservation(reservation);
    }

    @Override
    public Reservation getReservationByUser(long userId, long reservationId) {
        User user = this.userRepository.getUserById(userId).orElseThrow(
                UserDoesntExistException::new
        );
        return this.reservationRepository.getReservation(user, reservationId).orElseThrow(
                ReservationDoesntExistException::new
        );
    }

    @Override
    public Reservation addRoomToReservation(long userId, long reservationId, long roomId) {
        Reservation reservation = getReservationByUser(userId, reservationId);
        Room room = this.roomRepository.getRoomById(roomId).orElseThrow(
                RoomDoesntExistException::new
        );

        reservation.addRoomToReservation(room);

        return this.reservationRepository.saveRoomToReservation(reservation);
    }
}
