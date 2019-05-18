package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Order;
import se.sina.webshop.model.entity.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderItemNumber(UUID number);
    List<OrderItem> findAllByOrder(Order order);
    List<OrderItem> findAllByOrderOrderNumber(UUID orderNumber);
    List<OrderItem> findAllByOrderOrderNumberAndShipped(UUID orderNumber, Boolean shipped);
    List<OrderItem> findAllByShippedTrue();
    List<OrderItem> findAllByShippedFalse();
    List<OrderItem> findAllByShipped(Boolean shipped);
}
