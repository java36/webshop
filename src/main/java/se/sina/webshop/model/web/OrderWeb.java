package se.sina.webshop.model.web;

import java.sql.Date;
import java.util.UUID;

public final class OrderWeb {

    private UUID orderNumber;

    private Date orderDate;

    private CustomerWeb customer;

    private Double total;

    public OrderWeb() {
    }

    public OrderWeb(UUID orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderWeb(Date orderDate, CustomerWeb customer) {
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public OrderWeb(UUID orderNumber, Date orderDate, CustomerWeb customer, Double total ) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customer = customer;
        this.total = total;
    }

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public CustomerWeb getCustomer() {
        return customer;
    }

    public Double getTotal() {
        return total;
    }
}
