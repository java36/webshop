package se.sina.webshop.model.web;

import java.sql.Date;
import java.util.UUID;

public final class OrderItemWeb {

    private UUID orderItemNumber;

    private ItemWeb item;

    private OrderWeb order;

    private boolean shipped;

    private Date shippingDate;

    public OrderItemWeb() {

    }

    public OrderItemWeb(ItemWeb item, OrderWeb order) {
        this.item = item;
        this.order = order;
    }


    //post
    public OrderItemWeb(UUID orderItemNumber, ItemWeb item, OrderWeb order) {
        this.orderItemNumber = orderItemNumber;
        this.item = item;
        this.order = order;
    }

    public OrderItemWeb(UUID orderItemNumber, ItemWeb item, OrderWeb order, boolean shipped, Date shippingDate) {
        this.orderItemNumber = orderItemNumber;
        this.item = item;
        this.order = order;
        this.shipped = shipped;
        this.shippingDate = shippingDate;
    }

    public UUID getOrderItemNumber() {
        return orderItemNumber;
    }

    public ItemWeb getItem() {
        return item;
    }

    public OrderWeb getOrder() {
        return order;
    }

    public boolean isShipped() {
        return shipped;
    }

    public Date getShippingDate() {
        return shippingDate;
    }
}
