package onlineshop.example.beeshop.exception;

import onlineshop.example.beeshop.exception.exception.AccountNotFoundException;

import java.util.Map;

public interface ExceptionAdvice<T> {
    public Map<String, String> exceptionHandler(T exception);
}
