package se.sina.webshop.service.exception.CustomerExceptions;

public class CustomerEmailNotFound extends InvalidCustomerException {
    public CustomerEmailNotFound(String message) {
        super(message);
    }
}
