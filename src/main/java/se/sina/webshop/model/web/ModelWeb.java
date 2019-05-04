package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.Brand;
import se.sina.webshop.model.entity.ModelStatus;

import java.util.UUID;

public class ModelWeb {

    private UUID ModelNumber;

    private String name;

    private Brand brand;

    private ModelStatus modelStatus;

    public ModelWeb() {
    }

    public ModelWeb(String name, Brand brand) {
        this.name = name;
        this.brand = brand;
    }

    public ModelWeb(UUID modelNumber) {
        ModelNumber = modelNumber;
    }

    public ModelWeb(UUID modelNumber, String name, Brand brand, ModelStatus modelStatus) {
        ModelNumber = modelNumber;
        this.name = name;
        this.brand = brand;
        this.modelStatus = modelStatus;
    }

    public UUID getModelNumber() {
        return ModelNumber;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public ModelStatus getModelStatus() {
        return modelStatus;
    }
}
