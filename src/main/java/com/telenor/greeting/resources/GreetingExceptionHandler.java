package com.telenor.greeting.resources;

import com.telenor.greeting.error.Error;
import com.telenor.greeting.exception.InvalidRequestException;
import com.telenor.greeting.exception.UnImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GreetingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnImplementedException.class)
    public ResponseEntity<Error> handleUnImplementedException(UnImplementedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(Error.builder()
                .httpStatus(HttpStatus.NOT_IMPLEMENTED.value())
                .errorDescription(exception.getMessage()).build());
    }

    @ExceptionHandler({IllegalArgumentException.class, InvalidRequestException.class})
    public ResponseEntity<Error> handleInvalidRequestException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errorDescription(ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> unHandleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorDescription(ex.getMessage()).build());
    }
}