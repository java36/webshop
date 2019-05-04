package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.Category;

import java.util.UUID;

public class BrandWeb {

    private UUID brandNumber;

    private String name;

    private Category category;

    public BrandWeb() {
    }

    public BrandWeb(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public BrandWeb(UUID brandNumber, String name, Category category) {
        this.brandNumber = brandNumber;
        this.name = name;
        this.category = category;
    }

    public UUID getBrandNumber() {
        return brandNumber;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
