package se.sina.webshop.model.web;

import se.sina.webshop.model.entity.ItemStatus;
import se.sina.webshop.model.entity.Model;

import java.util.UUID;

public final class ItemWeb {

    private UUID itemNumber;

    private ItemStatus itemStatus;

    private Model model;

    public ItemWeb() {
    }

    public ItemWeb(UUID itemNumber) {
        this.itemNumber = itemNumber;
    }

    public ItemWeb(ItemStatus itemStatus, Model model) {
        this.itemStatus = itemStatus;
        this.model = model;
    }

    public ItemWeb(UUID itemNumber, ItemStatus itemStatus, Model model) {
        this.itemNumber = itemNumber;
        this.itemStatus = itemStatus;
        this.model = model;
    }

    public UUID getItemNumber() {
        return itemNumber;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public Model getModel() {
        return model;
    }
}
