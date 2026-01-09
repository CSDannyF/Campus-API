package be.ucll.backend.campusapi.repository.jpa;

import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);

    Reservation getReservationsByReservationIdAndUser_UserId(long reservationId, long userUserId);

    List<Reservation> findReservationByReservationIdAndUser_UserId(long reservationId, long userUserId);

    Optional<Reservation> getReservationsByReservationIdAndUser(long reservationId, User user);
}
