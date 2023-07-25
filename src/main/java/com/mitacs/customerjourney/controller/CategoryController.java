package com.mitacs.customerjourney.controller;

import com.mitacs.customerjourney.model.Category;
import com.mitacs.customerjourney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/api/v1/categories")
    public List<Category> findAllCategories(){
        return service.findAllCategories();
    }
}
