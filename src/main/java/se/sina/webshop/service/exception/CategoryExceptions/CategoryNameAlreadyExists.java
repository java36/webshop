package se.sina.webshop.service.exception.CategoryExceptions;

public class CategoryNameAlreadyExists extends InvalidCategoryException {
    public CategoryNameAlreadyExists(String message) {
        super(message);
    }
}
