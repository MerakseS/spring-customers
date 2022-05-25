package by.merakses.springcustomers.service;

import java.util.List;

import by.merakses.springcustomers.entity.Customer;

public interface CustomerService {

    List<Customer> getAll();

    void create(Customer customer);

    Customer get(long id);

    void update(long id, Customer customer);

    void delete(long id);
}
