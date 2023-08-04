package com.mitacs.customerjourney.model;

import com.mitacs.customerjourney.model.enums.BikeType;
import com.mitacs.customerjourney.model.enums.Colour;
import com.mitacs.customerjourney.model.enums.Material;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bike extends Product{
    private BikeType bikeType;
    private Integer speed;
    private String size;
    private Colour colour;
    private Material material;
    private Integer ageGraterThan;
}
