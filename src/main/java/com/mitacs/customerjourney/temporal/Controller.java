package com.mitacs.customerjourney.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private WorkflowClient workflowClient;

    @PostMapping("/start")
    public String startWorkflow(@RequestBody String customerId){
        final String WORKFLOW_ID = customerId;
        final String QUEUE_NAME = "TASKS";

        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID)
                .setTaskQueue(QUEUE_NAME)
                .build();

        CustomerJourneyWorkflow workflow = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, workflowOptions);
        workflow.executeCustomerJourney(customerId);
        return "Workflow running with ID >>> " + WORKFLOW_ID;
    }
}
