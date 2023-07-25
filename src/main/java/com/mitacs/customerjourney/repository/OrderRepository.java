package com.mitacs.customerjourney.repository;

import com.mitacs.customerjourney.model.ShoppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ShoppingOrder, Long> {
}
