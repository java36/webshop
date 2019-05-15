package se.sina.webshop.service.exception.BrandExceptions;

public class BrandNameAlreadyExists extends InvalidBrandException {
    public BrandNameAlreadyExists(String message) {
        super(message);
    }
}
