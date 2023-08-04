package com.mitacs.customerjourney.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mitacs.customerjourney.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ShoppingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private OrderStatus status;
    @OneToMany
    private List<Item> orderItems;
    @ManyToOne
    private Customer customer;
}
