package se.sina.webshop.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public final class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID categoryNumber;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    protected Category() {
    }

    public Category(UUID categoryNumber, String name) {
        this.categoryNumber = categoryNumber;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public UUID getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(UUID categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("id: %s Category Number: %s, name: %s", id, categoryNumber, name);
    }

}
