package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
import com.mitacs.customerjourney.temporal.payloads.*;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CustomerJourneyWorkflow {
    @WorkflowMethod
    void executeCustomerJourney(WorkflowInfo workflowInfo);

    @SignalMethod
    void receiveSubscriptionInfo(SubscriptionInfo subscriptionInfo);
//
//    @SignalMethod
//    void receiveChatbotCommunicationInfo(ChatbotCommunicationInfo chatbotCommunicationInfo);
//
//    @SignalMethod
//    void receiveFavoriteProductInfo(FavoriteProductInfo favoriteProductInfo);

    @SignalMethod
    void receiveTargetedProduct(TargetedProductInfo targetedProductInfo);
    @QueryMethod
    Stage getStage();

    @QueryMethod
    BrowsingType getBrowsingType();

    @QueryMethod
    String getWorkflowId();


}
