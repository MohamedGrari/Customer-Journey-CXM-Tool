package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Category;
import com.mitacs.customerjourney.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAllCategories(){
        return repository.findAll();
    }
}
