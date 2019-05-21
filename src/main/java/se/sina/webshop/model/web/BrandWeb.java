package se.sina.webshop.model.web;

import java.util.UUID;

public final class BrandWeb {

    private UUID brandNumber;

    private String name;

    private CategoryWeb category;

    private Boolean active;

    public BrandWeb() {
    }

    // for post
    public BrandWeb(String name, CategoryWeb category) {
        this.name = name;
        this.category = category;
    }

    public BrandWeb(UUID brandNumber, String name, CategoryWeb category, Boolean active) {
        this.brandNumber = brandNumber;
        this.name = name;
        this.category = category;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }
}
