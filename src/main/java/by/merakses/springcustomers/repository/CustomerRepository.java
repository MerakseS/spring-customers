package by.merakses.springcustomers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.merakses.springcustomers.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}