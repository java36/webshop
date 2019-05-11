package se.sina.webshop.service.exception.CategoryExceptions;

public class CategoryNameNotFound extends InvalidCategoryException {
    public CategoryNameNotFound(String message) {
        super(message);
    }
}
