package se.sina.webshop.service.exception.EmployeeExceptions;

public abstract class InvalidEmployeeException extends RuntimeException{

    public InvalidEmployeeException(String message) {
        super(message);
    }
}
