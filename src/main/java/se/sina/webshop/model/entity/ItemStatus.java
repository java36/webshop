package se.sina.webshop.model.entity;

public enum ItemStatus {

    STORED("stored"),
    TERMINATED("terminated"),
    SOLD("sold");

    private String name;

    ItemStatus(String name) {
        this.name = name;
    }
}
