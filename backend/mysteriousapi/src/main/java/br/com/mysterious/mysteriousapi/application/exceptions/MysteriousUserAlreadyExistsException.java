package br.com.mysterious.mysteriousapi.application.exceptions;

public class MysteriousUserAlreadyExistsException extends Exception {
    public MysteriousUserAlreadyExistsException(String message) {
        super(message);
    }
}
