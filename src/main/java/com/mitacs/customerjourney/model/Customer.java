package com.mitacs.customerjourney.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private String workflowId;
    @OneToOne
    private Cart cart;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<ShoppingOrder> orders;
    @JsonIgnore
    @ManyToMany
    private Set<Product> targetedProducts;

    public Customer(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }
}
