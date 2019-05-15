package se.sina.webshop.model.entity;

public enum ItemStatus {

    STORED("stored"),
    DELETED("deleted"),
    SOLD("sold");

    private String name;

    ItemStatus(String name) {
        this.name = name;
    }
}
