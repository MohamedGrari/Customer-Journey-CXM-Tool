package com.mitacs.customerjourney.temporal.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {
    private String customerId;
    private boolean isLoggedIn = false;
}
