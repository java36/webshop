package se.sina.webshop.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity(name = "[order]")
public final class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID orderNumber;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Date orderDate;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false)
    private double total;

    protected Order() {
    }

    public Order(UUID orderNumber, Boolean active, Date orderDate, Customer customer) {
        this.orderNumber = orderNumber;
        this.active = active;
        this.orderDate = orderDate;
        this.customer = customer;
        total = 0.0;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(UUID orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("id: %s, customer: %s, number: %s, active: %s, date: %s total: %s", id, customer.getId(), orderNumber, active, orderDate, total);
    }
}
