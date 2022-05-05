package by.merakses.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.merakses.hellospring.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}