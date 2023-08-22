package onlineshop.example.beeshop.exception.advice;

import onlineshop.example.beeshop.exception.ExceptionAdvice;
import onlineshop.example.beeshop.exception.exception.AccountNotFoundException;
import onlineshop.example.beeshop.filter.AdminRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AccountNotFoundAdvice implements ExceptionAdvice<AccountNotFoundException> {

    private Logger logger = LoggerFactory.getLogger(AdminRequestFilter.class);
    @Override
    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(AccountNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Detail", exception.getMessage());
        logger.error(exception.getMessage());
        return error;
    }
}
