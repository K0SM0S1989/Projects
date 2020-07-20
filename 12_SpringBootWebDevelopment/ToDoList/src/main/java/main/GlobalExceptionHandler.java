package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleContentNotAllowedException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }
}
