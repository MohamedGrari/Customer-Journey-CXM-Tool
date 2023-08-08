package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.enums.Message;
import com.mitacs.customerjourney.service.FrontendClient;
import org.springframework.stereotype.Component;


@Component
public class ActivitiesImpl implements Activities {

    private final FrontendClient frontendClient;

    public ActivitiesImpl(FrontendClient frontendClient) {
        this.frontendClient = frontendClient;
    }

    @Override
    public void inviteToSubscribe() {
        frontendClient.sendMessage(Message.INVITE_TO_SUBSCRIBE);
    }

    @Override
    public void communicateWithChatbot() {
        frontendClient.sendMessage(Message.COMMUNICATE_WITH_CHATBOT);
    }
}
