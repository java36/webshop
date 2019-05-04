package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.EmployeeRepository;

@Service
public final class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
