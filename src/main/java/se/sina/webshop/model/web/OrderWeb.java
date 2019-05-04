package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.Customer;

import java.sql.Date;
import java.util.UUID;

public final class OrderWeb {

    private UUID orderNumber;

    private Date orderDate;

    private Customer customer;

    private double total;

    public OrderWeb() {
    }

    public OrderWeb(Date orderDate, Customer customer) {
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public OrderWeb(UUID orderNumber, Date orderDate, Customer customer) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }
}
