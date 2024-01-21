package com.mitacs.customerjourney.controller;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.payload.LoginInfo;
import com.mitacs.customerjourney.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Optional<Customer> logIn(@RequestBody LoginInfo loginInfo){
        return service.logIn(loginInfo);
    }

    @GetMapping("api/v1/customers/{customerEmail}")
    public Optional<Customer> getCustomer(@PathVariable String customerEmail){
        return service.getCustomer(customerEmail);
    }

    @GetMapping("customers/{workflowId}")
    public Optional<Customer> getCustomerByWorkflowId(@PathVariable String workflowId){
        return service.getCustomerByWorkflowId(workflowId);
    }
}
