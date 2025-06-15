package br.com.deopraglabs.moneta.exceptions;

import br.com.deopraglabs.moneta.dtos.response.DefaultExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<DefaultExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<DefaultExceptionResponse> handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BRValidationException.class)
    public final ResponseEntity<DefaultExceptionResponse> handleBRValidationException(BRValidationException e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getErrors().toString(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DateTimeException.class)
    public final ResponseEntity<DefaultExceptionResponse> handleDateTimeException(DateTimeException e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DefaultExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getMostSpecificCause().getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<DefaultExceptionResponse> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)), HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public ResponseEntity<DefaultExceptionResponse> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException e, WebRequest request) {
        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException e, WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(new DefaultExceptionResponse(
                new Date(),
                errors.toString(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST
        );
    }
}
