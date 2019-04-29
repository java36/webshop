package se.sina.webshop.model.entity;

public enum ModelStatus {

    INSTORE("in store"),
    SOLDOUT("sold out");

    private String name;

    ModelStatus(String name) {
        this.name = name;
    }
}
