package be.ucll.backend.campusapi.error;

public class ReservationTimeException extends RuntimeException {
  public ReservationTimeException(String message) {
    super(message);
  }
}
