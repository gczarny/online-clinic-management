package pl.online_clinic_management.domain.exception;

public class ProcessingException extends RuntimeException{

    public ProcessingException(String message) {
        super(message);
    }
}
