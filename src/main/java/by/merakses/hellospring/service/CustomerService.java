package by.merakses.hellospring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.merakses.hellospring.entity.Customer;
import by.merakses.hellospring.repository.CustomerRepository;

@Service
//@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer get(long id) {
        return customerRepository.getById(id);
    }

    @Transactional
    public void update(long id, Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);
    }
}
