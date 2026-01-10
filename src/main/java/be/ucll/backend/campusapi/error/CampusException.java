package be.ucll.backend.campusapi.error;

public class CampusException extends RuntimeException {
    public CampusException(String message) {
        super(message);
    }
}
