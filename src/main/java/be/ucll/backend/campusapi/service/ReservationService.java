package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    List<Reservation> getAllUserReservations(long userId);
    Reservation addReservation(long userId, Reservation reservation);
    Reservation getReservationByUser(long userId, long reservationId);
    Reservation addRoomToReservation(long userId, long reservationId, long roomId);
}
