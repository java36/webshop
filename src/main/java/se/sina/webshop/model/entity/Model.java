package se.sina.webshop.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public final class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID ModelNumber;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne()
    private Brand brand;

    @Column(nullable = false)
    private ModelStatus modelStatus;

    private Boolean active;

    @Column(nullable = false)
    private double price;

    protected Model() {

    }

    public Model(UUID ModelNumber, String name, Brand brand, double price) {
        this.ModelNumber = ModelNumber;
        this.name = name;
        this.brand = brand;
        this.price = price;
        modelStatus = ModelStatus.INSTORE;
    }

    public Long getId() {
        return id;
    }

    public UUID getModelNumber() {
        return ModelNumber;
    }

    public ModelStatus getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(ModelStatus modelStatus) {
        this.modelStatus = modelStatus;
    }

    public void setModelNumber(UUID modelNumber) {
        this.ModelNumber = modelNumber;
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

    @Override
    public String toString() {
        return String.format("id: %s, Model Number: %s, name: %s, brand %s status", id, ModelNumber, name, brand, modelStatus);
    }
}
