package se.sina.webshop.service.exception.EmployeeExceptions;

public class InvalidUsernameOrPassword extends InvalidEmployeeException {
    public InvalidUsernameOrPassword(String message) {
        super(message);
    }
}
