package be.ucll.backend.campusapi.error;

public class RequiredFieldNameException extends RuntimeException {
    public RequiredFieldNameException(String message) {
        super(message);
    }
}
