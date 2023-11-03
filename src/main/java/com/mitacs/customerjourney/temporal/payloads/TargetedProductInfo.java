package com.mitacs.customerjourney.temporal.payloads;

import com.mitacs.customerjourney.model.enums.ProductType;
import com.mitacs.customerjourney.model.enums.TargetedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TargetedProductInfo {
    private TargetedProduct targetedProduct;
    private ProductType productType;
    private String workflowId;
}
