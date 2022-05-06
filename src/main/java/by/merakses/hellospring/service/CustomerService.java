package by.merakses.hellospring.service;

import java.util.List;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.merakses.hellospring.entity.Customer;
import by.merakses.hellospring.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        LOG.info("Getting all customers");
        return customerRepository.findAll();
    }

    @Transactional
    public void create(Customer customer) {
        customer = customerRepository.save(customer);
        LOG.info(format("Successfully created customer with id %d", customer.getId()));
    }

    public Customer get(long id) {
        LOG.info(format("Getting customer with id %d", id));
        return customerRepository.getById(id);
    }

    @Transactional
    public void update(long id, Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);
        LOG.info(format("Successfully updated customer with id %d", customer.getId()));
    }

    @Transactional
    public void delete(long id) {
        customerRepository.deleteById(id);
        LOG.info(format("Successfully deleted customer with id %d", id));
    }
}
