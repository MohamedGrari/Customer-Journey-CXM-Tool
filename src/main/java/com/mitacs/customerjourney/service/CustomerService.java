package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Cart;
import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.payload.LoginInfo;
import com.mitacs.customerjourney.repository.CartRepository;
import com.mitacs.customerjourney.repository.CustomerRepository;
import com.mitacs.customerjourney.temporal.CustomerJourneyWorkflow;
import com.mitacs.customerjourney.temporal.payloads.SubscriptionInfo;
import io.temporal.api.enums.v1.WorkflowExecutionStatus;
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.TerminateWorkflowExecutionRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowStub;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private WorkflowClient workflowClient;

    public Optional<Customer> signUp(Customer customer){
        if (repository.findById(customer.getEmail()).isPresent()){
            return Optional.empty();
        } else {
            repository.save(customer);
            return Optional.of(customer);
        }
    }

    public Optional<Customer> logIn(LoginInfo loginInfo){
        Optional<Customer> customer = repository.findById(loginInfo.getEmail());

        if (customer.isPresent() && customer.get().getPassword().equals(loginInfo.getPassword())){
            CustomerJourneyWorkflow workflow1 = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, loginInfo.getWorkflowId());
            SubscriptionInfo subscriptionInfo = new SubscriptionInfo(true, customer.get(), loginInfo.getWorkflowId());
            workflow1.receiveSubscriptionInfo(subscriptionInfo);


            if (!loginInfo.isLoggedIn()) {
                WorkflowStub workflow = workflowClient.newUntypedWorkflowStub(loginInfo.getWorkflowId());
                workflow.cancel();
                workflow.terminate("testtt");
            }

            return customer;
        } else if (customer.isPresent()) {
            return Optional.empty();
        } else {
            Cart cart = new Cart();
            cartRepository.save(cart);
            Customer newCustomer = new Customer(loginInfo.getEmail(), getNewCustomerName(), "425 Rue de la Montagne", loginInfo.getPassword(), loginInfo.getWorkflowId(), cart, new ArrayList<>(), new HashSet<>());
            if (loginInfo.getWorkflowId() == null) {
                UUID uuid = UUID.randomUUID();
                newCustomer.setWorkflowId(uuid.toString());
            }
            return Optional.of(repository.save(newCustomer));
        }
    }

    private Cookie setCookies(){
        String customerId = UUID.randomUUID().toString();
        return new Cookie("customer_id", customerId);
    }

    private String getNewCustomerName(){
        List<String> names = new ArrayList<>();
        names.add("Ahmed");
        names.add("Amani");
        names.add("Bilel");
        names.add("Sarra");
        names.add("Manel");
        names.add("Ali");
        names.add("Majdi");
        names.add("Fatma");
        names.add("Mariem");
        Random random = new Random();
        int randomIndex = random.nextInt(names.size());
        return names.get(randomIndex);
    }

    public Optional<Customer> getCustomer(String customerEmail) {
        return repository.findById(customerEmail);
    }

    public Optional<Customer> getCustomerByWorkflowId(String workflowId) {
        return repository.findByWorkflowId(workflowId);
    }
}
