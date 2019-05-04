package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
