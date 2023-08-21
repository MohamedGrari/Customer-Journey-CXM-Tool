package com.mitacs.customerjourney.controller;

import com.mitacs.customerjourney.model.Bike;
import com.mitacs.customerjourney.model.enums.BikeType;
import com.mitacs.customerjourney.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BikeController {
    @Autowired
    private BikeService service;

    @GetMapping("/api/v1/bikes/{bikeId}")
    public Bike findBikeById(@PathVariable String bikeId) {
        return service.findBikeById(bikeId);
    }

    @GetMapping("/api/v1/all-bikes")
    public List<Bike> findAllBikes() {
        return service.findAllBikes();
    }

    @GetMapping("/api/v1/bikes")
    public List<Bike> findBikesByType(@RequestParam BikeType type) {
        return service.findBikesByType(type);
    }

//    @GetMapping("/api/v1/categories/{categoryId}/products")
//    public List<Product> findProductsByCategory(@PathVariable Long categoryId) {
//        return service.findProductsByCategory(categoryId);
//    }
}
