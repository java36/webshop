package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
