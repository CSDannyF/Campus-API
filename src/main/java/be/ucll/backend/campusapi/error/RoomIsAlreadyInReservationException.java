package be.ucll.backend.campusapi.error;

public class RoomIsAlreadyInReservationException extends RuntimeException {
    public RoomIsAlreadyInReservationException(String message) {
        super(message);
    }
}
