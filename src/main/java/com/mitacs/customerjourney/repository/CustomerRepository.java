package com.mitacs.customerjourney.repository;

import com.mitacs.customerjourney.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
