package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.model.enums.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontendClient {

    private final SimpMessagingTemplate messagingTemplate;

    public FrontendClient(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(Message message) throws Exception {
        this.messagingTemplate.convertAndSend("/topic/message", message.toString());
        System.out.println("Message: " + message + " has been sent successfully");
    }

    public void sendRecommendedProducts(List<Product> recommendedProducts) {
        System.out.println("Message: Recommended products have been sent successfully");
    }
}
