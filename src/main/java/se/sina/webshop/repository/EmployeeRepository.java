package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByEmployeeNumber(UUID employeeNumber);
    List<Employee> findAllByActive(Boolean active);
    Optional<Employee> findByUsernameAndPasswordAndActiveTrue(String username, String password);
}
