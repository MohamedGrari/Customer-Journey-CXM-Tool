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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ref;
    private String name;
    private Float price;
    private String description;
    private String imageUrl;
    @OneToOne
    private Details details;
    @ManyToOne
    private Category category;
}
