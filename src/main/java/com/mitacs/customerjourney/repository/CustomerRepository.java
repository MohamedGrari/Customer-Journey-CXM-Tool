package com.mitacs.customerjourney.repository;

import com.mitacs.customerjourney.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByWorkflowId(String s);
}
