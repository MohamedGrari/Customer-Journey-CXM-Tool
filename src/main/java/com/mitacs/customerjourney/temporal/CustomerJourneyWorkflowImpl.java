package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
import com.mitacs.customerjourney.temporal.payloads.ChatbotCommunicationInfo;
import com.mitacs.customerjourney.temporal.payloads.SubscriptionInfo;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class CustomerJourneyWorkflowImpl implements CustomerJourneyWorkflow{

    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();
    private String customerId;
    private Stage stage;
    private BrowsingType browsingType;

    private boolean isSubscriptionInfoReceived;
    private SubscriptionInfo subscriptionInfo;
    private boolean isChatbotCommunicationInfoReceived;
    private ChatbotCommunicationInfo chatbotCommunicationInfo;
    private final Activities activities = Workflow.newActivityStub(Activities.class, options);

    @Override
    public void executeCustomerJourney(String customerId) {
        Workflow.sleep(Duration.ofSeconds(5));
        browsingType = BrowsingType.PLEASURE;
        stage = Stage.UNKNOWN;
        this.customerId = customerId;
        activities.inviteToSubscribe();
        Workflow.await(() -> isSubscriptionInfoReceived);
        System.out.println("the customer is subscribed ? " + subscriptionInfo.isSubscribed());
        Workflow.sleep(Duration.ofSeconds(5));
        stage = Stage.DESIRE;
        activities.communicateWithChatbot();
        Workflow.await(() -> isChatbotCommunicationInfoReceived);
        if (chatbotCommunicationInfo.isResponding()){
            System.out.println("Customer is responding to chatbot");
        }
        else
            System.out.println("Customer is NOT responding to chatbot");
        Workflow.sleep(Duration.ofSeconds(5));
    }

    @Override
    public void receiveSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        System.out.println("Subscription info received!");
        this.subscriptionInfo = subscriptionInfo;
        isSubscriptionInfoReceived = true;
    }

    @Override
    public void receiveChatbotCommunicationInfo(ChatbotCommunicationInfo chatbotCommunicationInfo) {
        System.out.println("Chatbot Communication Info received!");
        this.chatbotCommunicationInfo = chatbotCommunicationInfo;
        isChatbotCommunicationInfoReceived = true;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public BrowsingType getBrowsingType() {
        return browsingType;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }
}
