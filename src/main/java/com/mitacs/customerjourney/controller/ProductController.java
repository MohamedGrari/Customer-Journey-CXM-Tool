package com.mitacs.customerjourney.controller;

import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/api/v1/products/{productId}")
    public Product findProductById(@PathVariable String productId) {
        return service.findProductById(productId);
    }

//    @GetMapping("/api/v1/categories/{categoryId}/products")
//    public List<Product> findProductsByCategory(@PathVariable Long categoryId) {
//        return service.findProductsByCategory(categoryId);
//    }
}
