package se.sina.webshop.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public final class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID customerNumber;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String socialSecurityNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean active;

    private String username;

    private String password;

    protected Customer() {
    }

    public Customer(UUID customerNumber, String firstname, String lastname, String socialSecurityNumber, String address, String email, Boolean active) {
        this.customerNumber = customerNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.socialSecurityNumber = socialSecurityNumber;
        this.address = address;
        this.email = email;
        this.active = active;
    }

    public Customer(UUID customerNumber, String firstname, String lastname, String address, String email, Boolean active) {
        this.customerNumber = customerNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public UUID getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(UUID customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("id: %s, Customer Number: %s, First Name: %s, Last Name %s, Email Address: %s", id, customerNumber, firstname, lastname, email);
    }
}

