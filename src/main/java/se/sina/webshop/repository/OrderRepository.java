package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
