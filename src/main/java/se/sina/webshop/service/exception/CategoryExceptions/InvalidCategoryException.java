package se.sina.webshop.service.exception.CategoryExceptions;

public abstract class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException(String message) {
        super(message);
    }
}
