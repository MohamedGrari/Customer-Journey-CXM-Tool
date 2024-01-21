package com.mitacs.customerjourney.temporal.payloads;

import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.model.enums.ProductType;
import com.mitacs.customerjourney.model.enums.TargetedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartInfo {
    private Product product;
    private String workflowId;
}
