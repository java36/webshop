package se.sina.webshop.service.exception.CustomerExceptions;

public class CustomerNameNotFound extends InvalidCustomerException {
    public CustomerNameNotFound(String message) {
        super(message);
    }
}
