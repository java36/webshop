package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.ModelStatus;

import java.util.UUID;

public final class ModelWeb {

    private UUID modelNumber;

    private String name;

    private BrandWeb brand;

    private ModelStatus modelStatus;

    private Double price;

    private Boolean active;

    public ModelWeb() {
    }

    // Post
    public ModelWeb(String name, BrandWeb brand, Double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public ModelWeb(UUID modelNumber) {
        this.modelNumber = modelNumber;
    }

    public ModelWeb(UUID modelNumber, String name, BrandWeb brand, ModelStatus modelStatus, Double price, Boolean active) {
        this.modelNumber = modelNumber;
        this.name = name;
        this.brand = brand;
        this.modelStatus = modelStatus;
        this.price = price;
        this.active = active;
    }

    public UUID getModelNumber() {
        return modelNumber;
    }

    public String getName() {
        return name;
    }

    public BrandWeb getBrand() {
        return brand;
    }

    public ModelStatus getModelStatus() {
        return modelStatus;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getActive() {
        return active;
    }
}
