package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Bike;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.model.enums.BikeType;
import com.mitacs.customerjourney.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BikeService {
    @Autowired
    private BikeRepository repository;

    public Bike findBikeById(String bikeId){
        return repository.findById(bikeId).get();
    }

    public List<Bike> findAllBikes() {
        return repository.findAll();
    }

    public List<Bike> findBikesByType(BikeType type) {
        return repository.findBikesByBikeType(type);
    }

}
