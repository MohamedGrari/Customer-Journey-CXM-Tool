package com.mitacs.customerjourney.repository;

import com.mitacs.customerjourney.model.Bike;
import com.mitacs.customerjourney.model.enums.BikeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, String> {
    List<Bike> findBikesByBikeType(BikeType bikeType);
}
