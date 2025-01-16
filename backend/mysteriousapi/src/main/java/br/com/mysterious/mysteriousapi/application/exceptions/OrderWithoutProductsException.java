package br.com.mysterious.mysteriousapi.application.exceptions;

public class OrderWithoutProductsException extends Exception {
    public OrderWithoutProductsException(String message) {
        super(message);
    }
}
