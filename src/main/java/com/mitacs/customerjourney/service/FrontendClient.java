package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.enums.Message;
import org.springframework.stereotype.Service;

@Service
public class FrontendClient {

    public void sendMessage(Message message){
        System.out.println("Message: " + message + " has been sent successfully");
    }

}
