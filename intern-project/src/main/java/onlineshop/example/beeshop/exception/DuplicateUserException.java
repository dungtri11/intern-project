package onlineshop.example.beeshop.exception;

public class DuplicateUserException extends  RuntimeException{

    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
