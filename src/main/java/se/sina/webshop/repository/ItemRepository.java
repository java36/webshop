package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
