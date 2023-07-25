package com.mitacs.customerjourney.repository;

import com.mitacs.customerjourney.model.Category;
import com.mitacs.customerjourney.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findProductsByCategoryId(Long categoryId);
}
