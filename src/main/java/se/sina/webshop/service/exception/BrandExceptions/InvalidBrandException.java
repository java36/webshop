package se.sina.webshop.service.exception.BrandExceptions;

public abstract class InvalidBrandException extends RuntimeException {
    public InvalidBrandException(String message) {
        super(message);
    }
}
