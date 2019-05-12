package se.sina.webshop.service.exception.ModelExceptions;

public abstract class InvalidModelException extends RuntimeException {
    public InvalidModelException(String message) {
        super(message);
    }
}
