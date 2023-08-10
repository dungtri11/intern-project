package onlineshop.example.beeshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(AccountNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Detail", exception.getMessage());

        return error;
    }
}
