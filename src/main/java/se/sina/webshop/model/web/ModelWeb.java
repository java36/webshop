package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.ModelStatus;

import java.util.UUID;

public final class ModelWeb {

    private UUID ModelNumber;

    private String name;

    private BrandWeb brand;

    private ModelStatus modelStatus;

    private double price;

    public ModelWeb() {
    }

    public ModelWeb(String name, BrandWeb brand, double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public ModelWeb(UUID modelNumber) {
        ModelNumber = modelNumber;
    }

    public ModelWeb(UUID modelNumber, String name, BrandWeb brand, ModelStatus modelStatus, double price) {
        ModelNumber = modelNumber;
        this.name = name;
        this.brand = brand;
        this.modelStatus = modelStatus;
        this.price = price;
    }

    public UUID getModelNumber() {
        return ModelNumber;
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

    public double getPrice() {
        return price;
    }
}
