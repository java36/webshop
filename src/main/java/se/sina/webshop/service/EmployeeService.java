package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.Employee;
import se.sina.webshop.repository.EmployeeRepository;
import se.sina.webshop.service.exception.EmployeeExceptions.EmployeeUsernameError;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        check(employee.getUsername());
        employee.setEmployeeNumber(UUID.randomUUID());
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    public Employee find(UUID employeeNumber){
        return check(employeeNumber);
    }
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }


    public void check(String username){
        Optional<Employee> result = employeeRepository.findByUsername(username);
        if(result.isPresent()){
            throw new EmployeeUsernameError("Username already taken");
        }
    }

    public Employee check(UUID employeeNumber){
        Optional<Employee> employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        if(!employee.isPresent()){
            System.out.println("No employee found");
        }
        return employee.get();
    }
}
