package se.sina.webshop.service.exception.OrderExceptions;

public class OrderNumberNotFound extends InvalidOrderException {
    public OrderNumberNotFound(String message) {
        super(message);
    }
}
