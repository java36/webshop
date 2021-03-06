package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Item;
import se.sina.webshop.model.entity.ItemStatus;
import se.sina.webshop.model.entity.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemNumber(UUID number);
    List<Item> findAllByItemStatus(ItemStatus status);
    List<Item> findAllByModelNameAndItemStatus(String modelName, ItemStatus itemStatus);
}
