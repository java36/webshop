package se.sina.webshop.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public final class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Type(type = "uuid-char")
    private UUID modelNumber;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @ManyToOne()
    private Brand brand;

    @Column(nullable = false)
    private ModelStatus modelStatus;

    private Boolean active;

    @Column(nullable = false)
    private Double price;

    protected Model() {

    }

    public Model(UUID modelNumber, String name, Brand brand, Double price) {
        this.modelNumber = modelNumber;
        this.name = name;
        this.brand = brand;
        this.price = price;
        modelStatus = ModelStatus.INSTORE;
    }

    public Model(UUID modelNumber, String name, Brand brand, ModelStatus modelStatus, Double price) {
        this.modelNumber = modelNumber;
        this.name = name;
        this.brand = brand;
        this.modelStatus = modelStatus;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public UUID getModelNumber() {
        return modelNumber;
    }

    public ModelStatus getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(ModelStatus modelStatus) {
        this.modelStatus = modelStatus;
    }

    public void setModelNumber(UUID modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("id: %s, Model Number: %s, name: %s, brand %s status", id, modelNumber, name, brand, modelStatus);
    }
}
