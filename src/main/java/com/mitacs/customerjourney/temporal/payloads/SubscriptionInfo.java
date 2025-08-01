package com.mitacs.customerjourney.temporal.payloads;

import com.mitacs.customerjourney.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionInfo {
    private boolean isSubscribed;
    private Customer customer;
    private String workflowId;
}
