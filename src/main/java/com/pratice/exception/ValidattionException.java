package com.pratice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidattionException {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleServiceErrors(ResponseStatusException exception){
        return ResponseEntity.status(exception.getStatusCode())
                .body(Map.of("errors",exception.getReason()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationErrors(MethodArgumentNotValidException exception) {
        List<Map<String, Object>> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("field", fieldError.getField());
                    error.put("reason", fieldError.getDefaultMessage());
                    errors.add(error);
                });
        return Map.of("errors", errors);
    }
}
