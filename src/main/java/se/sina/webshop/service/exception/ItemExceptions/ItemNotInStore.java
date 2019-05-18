package se.sina.webshop.service.exception.ItemExceptions;

public class ItemNotInStore extends InvalidItemException {
    public ItemNotInStore(String message) {
        super(message);
    }
}
