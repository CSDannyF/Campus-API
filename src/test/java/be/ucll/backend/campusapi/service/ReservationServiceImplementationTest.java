package be.ucll.backend.campusapi.service;

import be.ucll.backend.campusapi.error.RequiredFieldNameException;
import be.ucll.backend.campusapi.error.ReservationTimeException;
import be.ucll.backend.campusapi.error.RoomException;
import be.ucll.backend.campusapi.model.Reservation;
import be.ucll.backend.campusapi.model.Room;
import be.ucll.backend.campusapi.model.User;
import be.ucll.backend.campusapi.repository.ReservationRepository;
import be.ucll.backend.campusapi.repository.RoomRepository;
import be.ucll.backend.campusapi.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceImplementationTest {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private ReservationService reservationService;

    @BeforeEach
    public void prepare() {
        reservationRepository = mock(ReservationRepository.class);
        userRepository = mock(UserRepository.class);
        roomRepository = mock(RoomRepository.class);
        reservationService = new ReservationServiceImplementation(reservationRepository, userRepository, roomRepository);
    }

    @Test
    public void addReservationSucces() {
        User user = new User();

        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalDateTime.of(3000, 1, 12, 10, 0));
        reservation.setEndTime(LocalDateTime.of(3000, 1, 12, 11, 0));

        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        when(reservationRepository.addReservation(any())).thenAnswer(i -> i.getArgument(0));

        Reservation reservation1 = reservationService.addReservation(1, reservation);

        assertNotNull(reservation1);
        assertEquals(user, reservation1.getUser());
    }

    @Test
    public void addReservationThrowsRequiredFieldNameExceptionWhenStartOrEndTimeIsEmpty() {
        Reservation reservation1 = new Reservation();
        reservation1.setStartTime(null);
        reservation1.setEndTime(LocalDateTime.of(3000, 1, 12, 11, 0));

        Reservation reservation2 = new Reservation();
        reservation2.setStartTime(LocalDateTime.of(3000, 1, 12, 11, 0));
        reservation2.setEndTime(null);

        assertThrows(RequiredFieldNameException.class, () -> reservationService.addReservation(1, reservation1));
        assertThrows(RequiredFieldNameException.class, () -> reservationService.addReservation(1, reservation2));
    }

    @Test
    public void WhenReservationEndTimeIsAfterStartTimeThenThrowReservationTimeException() {
        User user = new User();

        Reservation reservation1 = new Reservation();
        reservation1.setStartTime(LocalDateTime.of(3000, 1, 12, 13, 0));
        reservation1.setEndTime(LocalDateTime.of(3000, 1, 12, 11, 0));

        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        when(reservationRepository.addReservation(any())).thenAnswer(i -> i.getArgument(0));

        assertThrows(ReservationTimeException.class, () -> reservationService.addReservation(1, reservation1));
    }

    @Test
    public void WhenReservationIsInThePastThrowReservationTimeException() {
        User user = new User();

        Reservation reservation1 = new Reservation();
        reservation1.setStartTime(LocalDateTime.of(2015, 1, 12, 10, 0));
        reservation1.setEndTime(LocalDateTime.of(2015, 1, 12, 11, 0));

        when(userRepository.getUserById(1)).thenReturn(Optional.of(user));
        when(reservationRepository.addReservation(any())).thenAnswer(i -> i.getArgument(0));

        assertThrows(ReservationTimeException.class, () -> reservationService.addReservation(1, reservation1));
    }

    @Test
    public void WhenRoomHasAReservationOverlapThrowRoomException() {
        User user1 = new User();
        user1.setUserId(1);

        Room room = new Room();
        room.setRoomId(1);

        Reservation reservation1 = new Reservation();
        reservation1.setReservationId(1);
        reservation1.setRooms(new ArrayList<>());
        reservation1.setStartTime(LocalDateTime.of(3000, 1, 12, 10, 0));
        reservation1.setEndTime(LocalDateTime.of(3000, 1, 12, 11, 0));

        Reservation reservation2 = new Reservation();
        reservation2.setReservationId(2);
        reservation2.setRooms(new ArrayList<>());
        reservation2.setStartTime(LocalDateTime.of(3000, 1, 12, 10, 30));
        reservation2.setEndTime(LocalDateTime.of(3000, 1, 12, 11, 30));

        // First mocking for user, reservation1 and adding room to reservation1
        when(userRepository.getUserById(1)).thenReturn(Optional.of(user1));
        when(reservationRepository.addReservation(reservation1)).thenReturn(reservation1);
        when(reservationRepository.saveRoomToReservation(reservation1)).thenReturn(reservation1);
        when(reservationRepository.getReservation(user1, reservation1.getReservationId())).thenReturn(Optional.of(reservation1));
        when(roomRepository.getRoomById(room.getRoomId())).thenReturn(Optional.of(room));

        when(reservationRepository.addReservation(reservation2)).thenReturn(reservation2);
        when(reservationRepository.saveRoomToReservation(reservation2)).thenReturn(reservation2);
        when(reservationRepository.getReservation(user1, reservation2.getReservationId())).thenReturn(Optional.of(reservation2));

        reservation1.addRoomToReservation(room);
        reservation2.addRoomToReservation(room);


        assertThrows(RoomException.class, () -> reservationService.addRoomToReservation(user1.getUserId(), reservation2.getReservationId(), room.getRoomId()));
    }
}
