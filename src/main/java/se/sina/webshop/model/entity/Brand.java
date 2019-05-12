package se.sina.webshop.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public final class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Type(type = "uuid-char")
    private UUID brandNumber;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne()
    private Category category;

    @Column(nullable = false)
    private Boolean active;

    protected Brand() {
    }

    public Brand(UUID brandNumber, String name, Category category) {
        this.brandNumber = brandNumber;
        this.name = name;
        this.category = category;
    }

    public Brand(UUID brandNumber) {
        this.brandNumber = brandNumber;
    }

    public Long getId() {
        return id;
    }

    public UUID getBrandNumber() {
        return brandNumber;
    }

    public void setBrandNumber(UUID brandNumber) {
        this.brandNumber = brandNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("id: %s, Brand Number: %s, Name: %s, Category %s", id, brandNumber, name, category);
    }
}
