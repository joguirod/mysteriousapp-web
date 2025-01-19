package br.com.mysterious.mysteriousapi.application.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
