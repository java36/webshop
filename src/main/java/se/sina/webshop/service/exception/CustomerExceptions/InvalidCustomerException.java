package se.sina.webshop.service.exception.CustomerExceptions;

public class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(String message) {
        super(message);
    }
}
