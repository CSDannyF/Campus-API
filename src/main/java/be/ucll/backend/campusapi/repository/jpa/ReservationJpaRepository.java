package be.ucll.backend.campusapi.repository.jpa;

import be.ucll.backend.campusapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
}
