package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product findProductById(Long productId){
        return repository.findById(productId).get();
    }

    public List<Product> findProductsByCategory(Long categoryId) {
        return repository.findProductsByCategoryId(categoryId);
    }
}
