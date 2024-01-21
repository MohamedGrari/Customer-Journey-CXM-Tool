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
        System.out.println("workflowId = " + workflowInfo.getWorkflowId());
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

    @PostMapping("/api/v1/receive/targeted-product-info")
    public void receiveTargetedProductInfo(@RequestBody TargetedProductInfo targetedProductInfo){
        CustomerJourneyWorkflow workflow1 = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, targetedProductInfo.getWorkflowId());
        workflow1.receiveTargetedProduct(targetedProductInfo);
    }

    @PostMapping("/api/v1/receive/appointment-info")
    public void receiveAppointmentInfo(@RequestBody String workflowId){
        CustomerJourneyWorkflow workflow1 = workflowClient.newWorkflowStub(CustomerJourneyWorkflow.class, workflowId);
        workflow1.receiveAppointmentInfo();
    }

}
