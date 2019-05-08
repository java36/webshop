package se.sina.webshop.model.web;

import java.util.UUID;

public final class BrandWeb {

    private UUID brandNumber;

    private String name;

    private CategoryWeb category;

    public BrandWeb() {
    }

    public BrandWeb(String name, CategoryWeb category) {
        this.name = name;
        this.category = category;
    }

    public BrandWeb(UUID brandNumber, String name, CategoryWeb category) {
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

    public CategoryWeb getCategory() {
        return category;
    }
}
