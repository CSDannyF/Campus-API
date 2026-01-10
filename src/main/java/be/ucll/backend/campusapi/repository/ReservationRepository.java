package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository {

    List<Reservation> getAllReservations();

    Reservation addReservation(Reservation reservation);

    Optional<Reservation> getReservation(User user, long reservationId);

    List<Reservation> findByUser(User user);

    Reservation saveRoomToReservation(Reservation reservation);
}
