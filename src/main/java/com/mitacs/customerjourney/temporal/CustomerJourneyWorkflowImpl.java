package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
import com.mitacs.customerjourney.model.payload.LoginInfo;
import com.mitacs.customerjourney.temporal.payloads.*;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class CustomerJourneyWorkflowImpl implements CustomerJourneyWorkflow{

    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();
    private String workflowId;
    private Stage stage;
    private BrowsingType browsingType;
    private boolean isSubscribed;

    private final Activities activities = Workflow.newActivityStub(Activities.class, options);
    private Customer customer;
    private TargetedProductInfo targetedProductInfo;
    private boolean aProductHasBeenTargeted;


    @Override
    public void executeCustomerJourney(WorkflowInfo workflowInfo) {
        Workflow.sleep(Duration.ofSeconds(5));
        browsingType = BrowsingType.PLEASURE;
        stage = Stage.UNKNOWN;
        this.workflowId = workflowInfo.getWorkflowId();
        System.out.println("workflowInfo.isLoggedIN = " + workflowInfo.isLoggedIn());
        if (!workflowInfo.isLoggedIn()) {
            activities.inviteToSubscribe();
//            Workflow.await(() -> customerHasSubscribed);
//            System.out.println("the customer is subscribed ? " + subscriptionInfo.isSubscribed());
        }
        Workflow.sleep(Duration.ofSeconds(10));
        stage = Stage.DESIRE;
        activities.communicateWithChatbot();
        Workflow.await(() -> (aProductHasBeenTargeted));
        stage = Stage.INTENTION;
//        boolean isFirstTimeWithProduct = true;
//        if (isSubscribed)
//            isFirstTimeWithProduct = activities.isNewPurchaseCheck(targetedProduct, customer);
//        if (isFirstTimeWithProduct){
        activities.recommendProducts(targetedProductInfo);
//        } else {
//            activities.recommendPersonalisedProducts(targetedProduct, customer);
//        }

    }

    @Override
    public void receiveSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        System.out.println("Subscription info received!");
        this.customer = subscriptionInfo.getCustomer();
        isSubscribed = subscriptionInfo.isSubscribed();
    }

//    @Override
//    public void receiveChatbotCommunicationInfo(ChatbotCommunicationInfo chatbotCommunicationInfo) {
//        System.out.println("Chatbot Communication Info received!");
//        this.chatbotCommunicationInfo = chatbotCommunicationInfo;
//        this.targetedProduct = chatbotCommunicationInfo.getTargetedProduct();
//        customerHasRespondedToChatbot = true;
//    }

//    @Override
//    public void receiveFavoriteProductInfo(FavoriteProductInfo favoriteProductInfo) {
//        System.out.println("Favorite Product Info received!");
//        this.favoriteProductInfo = favoriteProductInfo;
//        this.targetedProduct = favoriteProductInfo.getProduct();
//        customerHasMarkedProductAsFavorite = true;
//    }

    @Override
    public void receiveTargetedProduct(TargetedProductInfo targetedProductInfo) {
        System.out.println("Targeted Product Info received!");
        this.targetedProductInfo = targetedProductInfo;
        this.aProductHasBeenTargeted = true;
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
    public String getWorkflowId() {
        return workflowId;
    }
}
