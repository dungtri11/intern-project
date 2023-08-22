package onlineshop.example.beeshop.exception.exception;

public class DuplicateUserException extends  RuntimeException{

    public DuplicateUserException(String message) {
        super(message);
    }

}
