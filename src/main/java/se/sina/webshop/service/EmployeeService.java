package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.Employee;
import se.sina.webshop.repository.EmployeeRepository;
import se.sina.webshop.service.exception.EmployeeExceptions.EmployeeNumberNotFound;
import se.sina.webshop.service.exception.EmployeeExceptions.EmployeeUsernameError;
import se.sina.webshop.service.exception.EmployeeExceptions.InvalidUsernameOrPassword;
import se.sina.webshop.service.exception.FormatExceptions.NameFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public final class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        check(employee.getUsername());
        checkUsernameFormat(employee.getUsername());
        checkNameFormat(employee.getFirstname());
        checkNameFormat(employee.getLastname());
        employee.setEmployeeNumber(UUID.randomUUID());
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    public Employee find(UUID employeeNumber){
        return check(employeeNumber);
    }
    public List<Employee> find(String username, String active){
        username = format(username);
        active = format(active);
        List<Employee> employees = new ArrayList<>();

        if(!isBlank(username)){
            Optional<Employee> result = employeeRepository.findByUsername(username);
            if(result.isPresent()){
                employees.add(result.get());
            }
            return employees;
        }
        else if(!isBlank(active)){
            return employeeRepository.findAllByActive(Boolean.valueOf(active));
        }
        return employeeRepository.findAll();
    }

    public Employee update(UUID employeeNumber, Employee employee){
        Employee existing = check(employeeNumber);
        if(employee.getUsername() != null){
            checkUsernameFormat(employee.getUsername());
            if(!employee.getUsername().equals(existing.getUsername())){
                check(employee.getUsername());
            }
            existing.setUsername(employee.getUsername());
        }
        if(employee.getPassword() != null){
            existing.setPassword(employee.getPassword());
        }
        if(employee.getFirstname() != null){
            checkNameFormat(employee.getFirstname());
            existing.setFirstname(employee.getFirstname());
        }
        if(employee.getLastname() != null){
            checkNameFormat(employee.getLastname());
            existing.setLastname(employee.getLastname());
        }
        return employeeRepository.save(existing);
    }

    public void delete(UUID employeeNumber){
        Employee employee = check(employeeNumber);
        employee.setActive(false);
        employeeRepository.save(employee);
    }
    public Employee authenticate(String username, String password){
        Optional<Employee> employee = employeeRepository.findByUsernameAndPasswordAndActiveTrue(username, password);
        if(!employee.isPresent()){
            throw new InvalidUsernameOrPassword("invalid Username Or Password");
        }
        return employee.get();
    }

    //checks to see if the username is already taken
    public void check(String username){
        Optional<Employee> result = employeeRepository.findByUsername(username);
        if(result.isPresent()){
            throw new EmployeeUsernameError("Username already taken");
        }
    }

    // checks if an employee with entered employee number exists and then returns it
    public Employee check(UUID employeeNumber){
        Optional<Employee> employee = employeeRepository.findByEmployeeNumber(employeeNumber);
        if(!employee.isPresent()){
            throw new EmployeeNumberNotFound("Employee number not found");
        }
        return employee.get();
    }

    //checks that the names entered by the employer dont contain any numbers or punctuations
    public void checkNameFormat(String name) {
        if (!name.matches("[A-Za-zÄÖÅäöå]+")) {
            throw new NameFormatException("Incorrect format for name");
        }
    }

    //checks that the username chosen by the employee corresponds to the format of a username
    public void checkUsernameFormat(String name) {
        if (!name.matches("[A-Za-zÄÖÅäöå0-9]+")) {
            throw new NameFormatException("Incorrect format for username");
        }
    }
    public String format(String string){
        return string.trim().toLowerCase();
    }

}
