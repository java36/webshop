package se.sina.webshop.model.web;

import java.util.UUID;

public final class CustomerWeb {

    private UUID customerNumber;

    private String firstname;

    private String lastname;

    private String socialSecurityNumber;

    private String address;

    private String email;

    private String username;

    private String password;

    private Boolean active;

    public CustomerWeb() {
    }

    public CustomerWeb(UUID customerNumber) {
        this.customerNumber = customerNumber;
    }

    // for post
    public CustomerWeb(String firstname, String lastname, String address, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }

    public CustomerWeb(UUID customerNumber, String firstname, String lastname, String address, String email, Boolean active) {
        this.customerNumber = customerNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.active = active;
    }

    public UUID getCustomerNumber() {
        return customerNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }
}
