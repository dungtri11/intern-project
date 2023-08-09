package onlineshop.example.beeshop.exception;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
