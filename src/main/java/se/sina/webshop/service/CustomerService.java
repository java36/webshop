package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.model.entity.Customer;
import se.sina.webshop.model.entity.Model;
import se.sina.webshop.repository.CustomerRepository;
import se.sina.webshop.service.exception.CustomerExceptions.CustomerEmailNotFound;
import se.sina.webshop.service.exception.CustomerExceptions.CustomerNumberNotFound;
import se.sina.webshop.service.exception.CustomerExceptions.EmailAlreadyExists;
import se.sina.webshop.service.exception.FormatExceptions.EmailFormatException;
import se.sina.webshop.service.exception.FormatExceptions.NameFormatException;
import se.sina.webshop.service.exception.ModelExceptions.ModelNameNotFound;
import se.sina.webshop.service.exception.ModelExceptions.ModelNumberNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public final class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        checkNameFormat(customer.getFirstname());
        checkNameFormat(customer.getLastname());
        checkEmailFormat(customer.getEmail());
        validate(customer.getEmail());
        customer.setCustomerNumber(UUID.randomUUID());
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    public Customer find(UUID customerNumber){
        return check(customerNumber);
    }

    public List<Customer> find(String email, String active){
        List<Customer> customers = new ArrayList<>();
        email = format(email);
        active = format(active);
        if(isBlank(active) || active.equals("true")){
            if(!isBlank(email)){
                Customer customer = check(email);
                if(customer.getActive()){
                    customers.add(customer);
                }
                return customers;
            }
            return customerRepository.findAllByActiveTrue();
        }
        else if(active.equals("false")) {
            if(!isBlank(email)){
                Customer customer = check(email);
                if(!customer.getActive()){
                    customers.add(customer);
                }
                return customers;
            }
            return customerRepository.findAllByActiveFalse();
        }
        return new ArrayList<>();
    }

    public Customer update(UUID customerNumber, Customer customer){
        Customer existing = check(customerNumber);
       if(customer.getFirstname() != null){
           checkNameFormat(customer.getFirstname());
           existing.setFirstname(customer.getFirstname());
       }
        if(customer.getLastname() != null){
            checkNameFormat(customer.getLastname());
            existing.setLastname(customer.getLastname());
        }
        if(customer.getSocialSecurityNumber() != null){
            existing.setSocialSecurityNumber(customer.getSocialSecurityNumber());
        }
        if(customer.getAddress() != null){
            existing.setAddress(customer.getAddress());
        }
        if(customer.getEmail() != null){
            checkEmailFormat(customer.getEmail());
            if(!customer.getEmail().equals(existing.getEmail())){
                validate(customer.getEmail());
                existing.setEmail(customer.getEmail());
            }
        }
        if(customer.getActive() != null){
            existing.setActive(customer.getActive());
        }
        if(customer.getUsername() != null){
            existing.setUsername(customer.getUsername());
        }
        if(customer.getPassword() != null){
            existing.setPassword(customer.getPassword());
        }

        return customerRepository.save(existing);
    }

    public Customer delete(UUID customerNumber){
        Customer customer = check(customerNumber);
        customer.setActive(false);
        return customerRepository.save(customer);
    }

    //checks to see if a customer with the entered number exists and returns it
    public Customer check(UUID number){

        Optional<Customer> result = customerRepository.findByCustomerNumber(number);
        if(!result.isPresent()){
            throw new CustomerNumberNotFound("Customer number not found");
        }
        return result.get();
    }

    // checks to see if a customer with the entered email exists and returns it
    public Customer check(String email){
        Optional<Customer> result = customerRepository.findByEmailAndActiveTrue(email);
        if(!result.isPresent()){
            throw new CustomerEmailNotFound("Customer's email address not found");
        }
        return result.get();
    }

    //checks if the email is already in use in the db
    public void validate(String email){
        Optional<Customer> result = customerRepository.findByEmail(email);
        if(result.isPresent()){
            throw new EmailAlreadyExists("The email address is already taken");
        }
    }

    //checks that the email entered by the user corresponds to the format of an email address
    public void checkEmailFormat(String email) {
        if (!email.matches("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+")) {
            throw new EmailFormatException("incorrect format for entered email");
        }
    }

    //checks that names entered by the user dont contain any numbers or punctuations
    public void checkNameFormat(String name) {
        if (!name.matches("[A-Za-zÄÖÅäöå]+")) {
            throw new NameFormatException("Incorrect format for name");
        }
    }

    public String format(String string){
        return string.trim().toLowerCase();
    }
}
