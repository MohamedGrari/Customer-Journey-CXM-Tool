package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Cart;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.repository.CartRepository;
import com.mitacs.customerjourney.temporal.CustomerJourneyWorkflow;
import com.mitacs.customerjourney.temporal.payloads.CartInfo;
import io.temporal.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private WorkflowClient workflowClient;

    public Cart addToCart(CartInfo cartInfo, Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.getProducts().add(cartInfo.getProduct());
        CustomerJourneyWorkflow workflow = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, cartInfo.getWorkflowId());
        workflow.receiveItemAddedToCart();
        return cartRepository.save(cart);
    }

    public Cart getCartItems(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    public void resetCart(Long cartId, String workflowId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setProducts(new ArrayList<>());
        CustomerJourneyWorkflow workflow = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, workflowId);
        workflow.receivePurchaseProceeded();
        cartRepository.save(cart);
    }
}
