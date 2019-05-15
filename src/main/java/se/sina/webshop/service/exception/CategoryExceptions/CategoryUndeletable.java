package se.sina.webshop.service.exception.CategoryExceptions;

public class CategoryUndeletable extends InvalidCategoryException {
    public CategoryUndeletable(String message) {
        super(message);
    }
}
