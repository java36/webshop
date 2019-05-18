package se.sina.webshop.service.exception.OrderExceptions;

public abstract class InvalidOrderException extends RuntimeException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
