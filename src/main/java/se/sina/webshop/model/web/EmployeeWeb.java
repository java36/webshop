package se.sina.webshop.model.web;

import java.util.UUID;

public final class EmployeeWeb {

    private UUID employeeNumber;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private Boolean active;

    public EmployeeWeb() {
    }

    public EmployeeWeb(UUID employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    //for post
    public EmployeeWeb(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public EmployeeWeb(UUID employeeNumber, String username, String password, String firstname, String lastname, Boolean active) {
        this.employeeNumber = employeeNumber;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = active;
    }

    public UUID getEmployeeNumber() {
        return employeeNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Boolean getActive() {
        return active;
    }
}
