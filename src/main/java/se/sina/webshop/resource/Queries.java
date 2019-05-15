package se.sina.webshop.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.UUID;

public final class Queries {
    @QueryParam("name")
    @DefaultValue("")
    private String name;
    @QueryParam("username")
    @DefaultValue("")
    private String username;
    @QueryParam("password")
    @DefaultValue("")
    private String password;
    @QueryParam("active")
    @DefaultValue("")
    private String active;
    @QueryParam("orderNumber")
    @DefaultValue("")
    private UUID orderNumber;
    @QueryParam("customerNumber")
    @DefaultValue("")
    private UUID customerNumber;

    @QueryParam("itemNumber")
    @DefaultValue("")
    private UUID itemNumber;

    @QueryParam("email")
    @DefaultValue("")
    private String email;
    @QueryParam("category")
    @DefaultValue("")
    private String category;

    @QueryParam("brand")
    @DefaultValue("")
    private String brand;
    @QueryParam("model")
    @DefaultValue("")
    private String model;
    @QueryParam("modelStatus")
    @DefaultValue("")
    private String modelStatus;
    @QueryParam("itemStatus")
    @DefaultValue("")
    private String itemStatus;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getActive() {
        return active;
    }

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public UUID getCustomerNumber() {
        return customerNumber;
    }

    public UUID getItemNumber() {
        return itemNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getModelStatus() {
        return modelStatus;
    }

    public String getItemStatus() {
        return itemStatus;
    }
}
