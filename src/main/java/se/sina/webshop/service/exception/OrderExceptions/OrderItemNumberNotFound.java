package se.sina.webshop.service.exception.OrderExceptions;

public class OrderItemNumberNotFound extends InvalidOrderException {
    public OrderItemNumberNotFound(String message) {
        super(message);
    }
}
