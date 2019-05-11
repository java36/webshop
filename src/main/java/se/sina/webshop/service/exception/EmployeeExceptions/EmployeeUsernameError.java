package se.sina.webshop.service.exception.EmployeeExceptions;

public class EmployeeUsernameError extends InvalidEmployeeException{
    public EmployeeUsernameError(String message) {
        super(message);
    }
}
