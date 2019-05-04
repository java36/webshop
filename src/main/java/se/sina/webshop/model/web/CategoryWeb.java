package se.sina.webshop.model.web;

import java.util.UUID;

public final class CategoryWeb {

    private UUID categoryNumber;

    private String name;

    public CategoryWeb() {
    }

    public CategoryWeb(UUID categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public CategoryWeb(String name) {
        this.name = name;
    }

    public CategoryWeb(UUID categoryNumber, String name) {
        this.categoryNumber = categoryNumber;
        this.name = name;
    }

    public UUID getCategoryNumber() {
        return categoryNumber;
    }

    public String getName() {
        return name;
    }
}
