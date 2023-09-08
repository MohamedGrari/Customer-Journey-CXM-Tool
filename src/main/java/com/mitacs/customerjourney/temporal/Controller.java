package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.temporal.payloads.*;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private WorkflowClient workflowClient;

    private String WORKFLOW_ID;
    private CustomerJourneyWorkflow workflow;

    @PostMapping("/api/v1/start-workflow")
    public String startWorkflow(@RequestBody WorkflowInfo workflowInfo){

        WORKFLOW_ID = workflowInfo.getWorkflowId();
        final String QUEUE_NAME = "TASKS";

        System.out.println("workflowInfo.isLoggedIN = " + workflowInfo.isLoggedIn());

        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID)
                .setTaskQueue(QUEUE_NAME)
                .build();

        workflow = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, workflowOptions);
        workflow.executeCustomerJourney(workflowInfo);
        return "Workflow running with ID >>> " + WORKFLOW_ID;
    }
//    @PostMapping("/receive/subscription-info")
//    public void receiveSubscriptionInfo(@RequestBody SubscriptionInfo subscriptionInfo){
//        CustomerJourneyWorkflow workflow1 = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, subscriptionInfo.getWorkflowId());
//        workflow1.receiveSubscriptionInfo(subscriptionInfo);
//    }

//    @PostMapping("/receive/chatbot-communication-info")
//    public void receiveChatbotCommunicationInfo(@RequestBody ChatbotCommunicationInfo chatbotCommunicationInfo){
//        workflow.receiveChatbotCommunicationInfo(chatbotCommunicationInfo);
//    }
//
//    @PostMapping("/receive/favorite-product-info")
//    public void receiveChatbotCommunicationInfo(@RequestBody FavoriteProductInfo favoriteProductInfo){
//        workflow.receiveFavoriteProductInfo(favoriteProductInfo);
//    }

    @PostMapping("/api/v1/receive/targeted-product-info")
    public void receiveTargetedProductInfo(@RequestBody TargetedProductInfo targetedProductInfo){
        workflow.receiveTargetedProduct(targetedProductInfo);
    }

}
