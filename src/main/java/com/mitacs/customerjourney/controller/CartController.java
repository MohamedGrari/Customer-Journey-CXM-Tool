package com.mitacs.customerjourney.controller;

import com.mitacs.customerjourney.model.Cart;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.service.CartService;
import com.mitacs.customerjourney.temporal.payloads.CartInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/addProduct")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, @RequestBody CartInfo cartInfo) {
        Cart updatedCart = cartService.addToCart(cartInfo, cartId);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{cartId}/reset")
    public void resetCart(@PathVariable Long cartId, @RequestBody String workflowId) {
        cartService.resetCart(cartId, workflowId);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartItems(@PathVariable Long cartId) {
        Cart cart = cartService.getCartItems(cartId);
        return ResponseEntity.ok(cart);
    }
}
