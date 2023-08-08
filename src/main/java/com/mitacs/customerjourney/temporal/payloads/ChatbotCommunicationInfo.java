package com.mitacs.customerjourney.temporal.payloads;

import com.mitacs.customerjourney.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotCommunicationInfo {
    private boolean isResponding;
    private boolean isTargeting;
    private Product targetedProduct;
}
