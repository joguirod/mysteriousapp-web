package br.com.mysterious.mysteriousapi.presentation.exceptions;

import br.com.mysterious.mysteriousapi.application.exceptions.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<HttpExceptionMessage> RunTimeException(RuntimeException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage("An internal server error ocurred. Try again later",
                HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<HttpExceptionMessage> ProductNotFoundException(ProductNotFoundException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NonPositiveNumberException.class)
    private ResponseEntity<HttpExceptionMessage> NonPositiveNumberException(NonPositiveNumberException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequiredValueException.class)
    private ResponseEntity<HttpExceptionMessage> RequiredValueException(RequiredValueException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    private ResponseEntity<HttpExceptionMessage> OrderNotFoundException(OrderNotFoundException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderWithoutProductsException.class)
    private ResponseEntity<HttpExceptionMessage> OrderWithoutProcutsException(OrderWithoutProductsException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MysteriousUserAlreadyExistsException.class)
    private ResponseEntity<HttpExceptionMessage> MysteriousUserAlreadyExistsException(MysteriousUserAlreadyExistsException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MysteriousUserNotFoundException.class)
    private ResponseEntity<HttpExceptionMessage> MysteriousUserNotFoundException(MysteriousUserNotFoundException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MysteriousCustomerNotFoundException.class)
    private ResponseEntity<HttpExceptionMessage> MysteriousCustomerNotFoundException(MysteriousCustomerNotFoundException e) {
        HttpExceptionMessage exceptionResponse = new HttpExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
