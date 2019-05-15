package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerNumber(UUID customerNumber);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByFirstname(String firstname);
    Optional<Customer> findByLastname(String lastname);
    List<Customer> findAllByActiveTrue();
    List<Customer> findAllByActiveFalse();
}
