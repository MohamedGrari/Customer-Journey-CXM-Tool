package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
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
    private boolean itemAddedToCart;
    private boolean anAppointmentHasBeenScheduled;
    private boolean purchaseProceeded;

    private final Activities activities = Workflow.newActivityStub(Activities.class, options);
    private Customer customer;
    private TargetedProductInfo targetedProductInfo;
    private boolean aProductHasBeenTargeted;


    @Override
    public void executeCustomerJourney(WorkflowInfo workflowInfo) {
        browsingType = BrowsingType.PLEASURE;
        stage = Stage.UNKNOWN;
        this.workflowId = workflowInfo.getWorkflowId();
        Workflow.sleep(Duration.ofSeconds(5));
        if (!workflowInfo.isLoggedIn()) {
            activities.inviteToSubscribe();
        }
        Workflow.sleep(Duration.ofSeconds(10));
        stage = Stage.DESIRE;
        activities.communicateWithChatbot();
        Workflow.await(() -> (aProductHasBeenTargeted || itemAddedToCart));
        stage = Stage.INTENTION;
        if (aProductHasBeenTargeted)
            activities.recommendProducts(targetedProductInfo);
        Workflow.await(() -> (itemAddedToCart || anAppointmentHasBeenScheduled));
        stage = Stage.TRYING;
        Workflow.await(() -> (purchaseProceeded));
        stage = Stage.PURCHASE;
        System.out.println("Customer Journey Completed!");

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
    public void receiveItemAddedToCart() {
        System.out.println("Item get Added to the Cart!");
        this.itemAddedToCart = true;
    }

    @Override
        public void receiveAppointmentInfo() {
        System.out.println("Appointment info received!");
        anAppointmentHasBeenScheduled = true;
    }

    @Override
    public void receivePurchaseProceeded() {
        System.out.println("Purchase Proceeded successfully!");
        this.purchaseProceeded = true;
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
