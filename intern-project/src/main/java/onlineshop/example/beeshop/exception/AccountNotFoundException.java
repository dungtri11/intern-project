package onlineshop.example.beeshop.exception;

public class AccountNotFoundException extends EntityNotFoundException{

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
