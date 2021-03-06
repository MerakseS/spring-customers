package by.merakses.springcustomers.service;

import java.util.List;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.merakses.springcustomers.entity.Customer;
import by.merakses.springcustomers.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class DefaultCustomerService implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCustomerService.class);

    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        LOG.info("Getting all customers");
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public void create(Customer customer) {
        customer = customerRepository.save(customer);
        LOG.info(format("Successfully created customer with id %d", customer.getId()));
    }

    @Override
    public Customer get(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        LOG.info(format("Successfully got customer with id %d", id));
        return customer;
    }

    @Transactional
    @Override
    public void update(long id, Customer customer) {
        customerRepository.findById(id).orElseThrow();
        customer.setId(id);
        customerRepository.save(customer);
        LOG.info(format("Successfully updated customer with id %d", customer.getId()));
    }

    @Transactional
    @Override
    public void delete(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customer);
        LOG.info(format("Successfully deleted customer with id %d", id));
    }
}
