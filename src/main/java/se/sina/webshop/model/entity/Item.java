package se.sina.webshop.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public final class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Type(type = "uuid-char")
    private UUID itemNumber;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @ManyToOne()
    private Model model;

    protected Item() {
    }

    public Item(UUID itemNumber, ItemStatus itemStatus, Model model) {
        this.itemNumber = itemNumber;
        this.itemStatus = itemStatus;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public UUID getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(UUID itemNumber) {
        this.itemNumber = itemNumber;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("id: %s, ItemNumber: %s, Status: %s, Model %s", id, itemNumber, itemStatus, model);
    }
}
