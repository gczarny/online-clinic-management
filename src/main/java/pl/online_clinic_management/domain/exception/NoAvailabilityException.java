package pl.online_clinic_management.domain.exception;

public class NoAvailabilityException extends RuntimeException{

    public NoAvailabilityException(final String message) {
        super(message);
    }
}
