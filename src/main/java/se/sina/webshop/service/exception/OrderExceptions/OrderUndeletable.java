package se.sina.webshop.service.exception.OrderExceptions;

public class OrderUndeletable extends InvalidOrderException{
    public OrderUndeletable(String message) {
        super(message);
    }
}
