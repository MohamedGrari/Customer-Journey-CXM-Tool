package com.mitacs.customerjourney.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    private String ref;
    private String name;
    private Float price;
    @Column(length = 10000)
    private String description;
    private String imageUrl;
    @ManyToOne
    private Brand brand;
}
