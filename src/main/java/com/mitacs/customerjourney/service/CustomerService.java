package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Optional<Customer> signUp(Customer customer){
        if (repository.findById(customer.getEmail()).isPresent()){
            return Optional.empty();
        } else {
            repository.save(customer);
            return Optional.of(customer);
        }
    }

    public Optional<Customer> logIn(Customer customerInput){
        Optional<Customer> customer = repository.findById(customerInput.getEmail());
        if (customer.isPresent() && customer.get().getPassword().equals(customerInput.getPassword())){
            return customer;
        }
        return Optional.empty();
    }
}
