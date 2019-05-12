package se.sina.webshop.service.exception.ItemExceptions;

public abstract class InvalidItemException extends RuntimeException{
    public InvalidItemException(String message) {
        super(message);
    }
}
