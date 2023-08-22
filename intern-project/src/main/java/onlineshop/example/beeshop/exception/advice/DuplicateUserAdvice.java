package onlineshop.example.beeshop.exception.advice;

import onlineshop.example.beeshop.exception.ExceptionAdvice;
import onlineshop.example.beeshop.exception.exception.DuplicateUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DuplicateUserAdvice implements ExceptionAdvice<DuplicateUserException> {
    private Logger logger = LoggerFactory.getLogger(DuplicateUserAdvice.class);
    @Override
    @ResponseBody
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> exceptionHandler(DuplicateUserException exception) {
        logger.error(exception.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("Detail", exception.getMessage());
        return error;
    }



}
