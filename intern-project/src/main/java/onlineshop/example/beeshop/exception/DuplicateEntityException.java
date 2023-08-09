package onlineshop.example.beeshop.exception;

public class DuplicateEntityException extends RuntimeException{

    public DuplicateEntityException(String message) {
        super(message);
    }

    public DuplicateEntityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
