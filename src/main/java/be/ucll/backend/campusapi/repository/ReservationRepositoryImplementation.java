package be.ucll.backend.campusapi.repository;

import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.repository.jpa.ReservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryImplementation implements ReservationRepository {

    private ReservationJpaRepository reservationJpaRepository;

    @Autowired
    public ReservationRepositoryImplementation(ReservationJpaRepository reservationJpaRepository) {
        this.reservationJpaRepository = reservationJpaRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationJpaRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return this.reservationJpaRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getReservation(User user, long reservationId) {
        return this.reservationJpaRepository.getReservationsByReservationIdAndUser(reservationId, user);
    }

    @Override
    public List<Reservation> findByUser(User user) {
        return this.reservationJpaRepository.findAllByUser(user);
    }

    @Override
    public Reservation saveRoomToReservation(Reservation reservation) {
        return this.reservationJpaRepository.save(reservation);
    }
}
