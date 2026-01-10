package be.ucll.backend.campusapi.error;

public class ReservationDoesntMatchUserException extends RuntimeException {
    public ReservationDoesntMatchUserException(String message) {
        super(message);
    }
}
