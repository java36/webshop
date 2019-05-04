package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.Item;
import se.sina.webshop.model.entity.Order;

import java.util.UUID;

public class OrderItemWeb {

    private UUID orderItemNumber;

    private Item item;

    private Order order;

    private boolean shipped;

    public OrderItemWeb() {

    }

    public OrderItemWeb(Item item, Order order) {
        this.item = item;
        this.order = order;
    }

    public OrderItemWeb(UUID orderItemNumber, Item item, Order order, boolean shipped) {
        this.orderItemNumber = orderItemNumber;
        this.item = item;
        this.order = order;
        this.shipped = shipped;
    }

    public UUID getOrderItemNumber() {
        return orderItemNumber;
    }

    public Item getItem() {
        return item;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isShipped() {
        return shipped;
    }
}
