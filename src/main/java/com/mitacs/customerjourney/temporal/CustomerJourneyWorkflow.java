package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.checkerframework.checker.guieffect.qual.SafeType;

@WorkflowInterface
public interface CustomerJourneyWorkflow {
    @WorkflowMethod
    void executeCustomerJourney(String customerId);

    @QueryMethod
    Stage getStage();

    @QueryMethod
    BrowsingType getBrowsingType();

    @QueryMethod
    String getCustomerId();
}
