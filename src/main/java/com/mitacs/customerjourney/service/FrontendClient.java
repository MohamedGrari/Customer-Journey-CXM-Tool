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

    // TODO Handle the websocket connection by adding the workflow id to the topic to prevent broadcasting
    public void sendMessage(Message message) throws Exception {
        this.messagingTemplate.convertAndSend("/topic/message", message.toString());
        System.out.println("Message: " + message + " has been sent successfully");
    }
}
