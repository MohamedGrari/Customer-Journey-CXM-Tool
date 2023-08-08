package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.temporal.payloads.ChatbotCommunicationInfo;
import com.mitacs.customerjourney.temporal.payloads.CustomerInfo;
import com.mitacs.customerjourney.temporal.payloads.SubscriptionInfo;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.api.common.v1.WorkflowExecutionOrBuilder;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowException;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.workflow.ExternalWorkflowStub;
import io.temporal.workflow.Workflow;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private WorkflowClient workflowClient;

    private String WORKFLOW_ID;
    private CustomerJourneyWorkflow workflow;
    @PostMapping("/start-workflow")
    public String startWorkflow(@RequestBody CustomerInfo customerInfo){

        WORKFLOW_ID = customerInfo.getCustomerId();
        final String QUEUE_NAME = "TASKS";

        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID)
                .setTaskQueue(QUEUE_NAME)
                .build();

        workflow = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, workflowOptions);
        workflow.executeCustomerJourney(customerInfo.getCustomerId());
        return "Workflow running with ID >>> " + WORKFLOW_ID;
    }
    @PostMapping("/receive/subscription-info")
    public void receiveSubscriptionInfo(@RequestBody SubscriptionInfo subscriptionInfo){
        workflow.receiveSubscriptionInfo(subscriptionInfo);
    }

    @PostMapping("/receive/chatbot-communication-info")
    public void receiveChatbotCommunicationInfo(@RequestBody ChatbotCommunicationInfo chatbotCommunicationInfo){
        workflow.receiveChatbotCommunicationInfo(chatbotCommunicationInfo);
    }

}
