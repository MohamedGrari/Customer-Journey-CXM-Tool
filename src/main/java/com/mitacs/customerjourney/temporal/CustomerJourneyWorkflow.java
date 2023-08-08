package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
import com.mitacs.customerjourney.temporal.payloads.ChatbotCommunicationInfo;
import com.mitacs.customerjourney.temporal.payloads.SubscriptionInfo;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.checkerframework.checker.guieffect.qual.SafeType;

@WorkflowInterface
public interface CustomerJourneyWorkflow {
    @WorkflowMethod
    void executeCustomerJourney(String customerId);

    @SignalMethod
    void receiveSubscriptionInfo(SubscriptionInfo subscriptionInfo);

    @SignalMethod
    void receiveChatbotCommunicationInfo(ChatbotCommunicationInfo chatbotCommunicationInfo);
    @QueryMethod
    Stage getStage();

    @QueryMethod
    BrowsingType getBrowsingType();

    @QueryMethod
    String getCustomerId();

}
