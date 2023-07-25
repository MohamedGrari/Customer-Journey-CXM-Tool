package com.mitacs.customerjourney.controller;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping("api/v1/auth/signup")
    public Optional<Customer> signUp(@RequestBody Customer customer){
        return service.signUp(customer);
    }

    @PostMapping("api/v1/auth/login")
    public Optional<Customer> logIn(@RequestBody Customer customer){
        return service.logIn(customer);
    }
}
