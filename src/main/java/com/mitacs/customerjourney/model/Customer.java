package com.mitacs.customerjourney.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    private String email;
    private String name;
    private String address;
    private String password;
    @OneToOne
    private Cart cart;
    @OneToMany(mappedBy = "customer")
    private List<ShoppingOrder> orders;
    @ManyToMany
    private List<Product> targetedProducts;
}
