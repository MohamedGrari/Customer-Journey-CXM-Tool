package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Cart;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.repository.CartRepository;
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

    public Cart addToCart(Product product, Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public Cart getCartItems(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    public void resetCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);
    }
}
