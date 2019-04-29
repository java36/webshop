package se.sina.webshop.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
public final class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID orderItemNumber;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Order order;

    private boolean shipped;

    protected OrderItem() {
    }

    public OrderItem(UUID orderItemNumber, Item item, Order order) {
        this.orderItemNumber = orderItemNumber;
        this.item = item;
        this.order = order;
        shipped = false;
    }

    public Long getId() {
        return id;
    }

    public UUID getOrderItemNumber() {
        return orderItemNumber;
    }

    public void setOrderItemNumber(UUID orderItemNumber) {
        this.orderItemNumber = orderItemNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    @Override
    public String toString() {
        return String.format("id: %s, number: %s; item: %s, order: %s", id, orderItemNumber, item.getId(), order.getId());
    }
}
