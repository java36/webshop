package se.sina.webshop.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Customer;
import se.sina.webshop.model.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(UUID number);
    List<Order> findAllByCustomerEmailAndActive(String customerEmail, Boolean active);
    List<Order> findAllByCustomerEmail(String customerEmail);
    List<Order> findAllByActive(Boolean active);
    List<Order> findAllByActiveFalse();
    List<Order> findAllByActiveTrue();
}
