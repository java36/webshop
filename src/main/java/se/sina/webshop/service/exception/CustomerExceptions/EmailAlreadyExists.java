package se.sina.webshop.service.exception.CustomerExceptions;

public class EmailAlreadyExists extends InvalidCustomerException{
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
