package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.enums.BrowsingType;
import com.mitacs.customerjourney.model.enums.Stage;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class CustomerJourneyWorkflowImpl implements CustomerJourneyWorkflow{

    private String customerId;
    private Stage stage;
    private BrowsingType browsingType;
    @Override
    public void executeCustomerJourney(String customerId) {
        Workflow.sleep(Duration.ofSeconds(5));
        browsingType = BrowsingType.PLEASURE;
        stage = Stage.UNKNOWN;
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
