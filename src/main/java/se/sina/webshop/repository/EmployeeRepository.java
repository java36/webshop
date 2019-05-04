package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
