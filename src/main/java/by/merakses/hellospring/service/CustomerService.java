package by.merakses.hellospring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.merakses.hellospring.entity.Customer;
import by.merakses.hellospring.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public void create(Customer customer) {
        customerRepository.save(customer);
    }
}
