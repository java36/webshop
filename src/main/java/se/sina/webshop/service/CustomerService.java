package se.sina.webshop.service;

import org.springframework.stereotype.Service;
import se.sina.webshop.repository.CustomerRepository;

@Service
public final class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
