package com.mitacs.customerjourney.config;

import com.mitacs.customerjourney.service.FrontendClient;
import com.mitacs.customerjourney.service.TemporalService;
import com.mitacs.customerjourney.temporal.ActivitiesImpl;
import com.mitacs.customerjourney.temporal.CustomerJourneyWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    @Bean
    public WorkflowServiceStubs serviceStubs() {
        return WorkflowServiceStubs.newLocalServiceStubs();
    }

    @Bean
    public WorkflowClient client(WorkflowServiceStubs serviceStubs) {
        return WorkflowClient.newInstance(serviceStubs);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient client) {
        return WorkerFactory.newInstance(client);
    }

    @Bean
    public Worker worker(WorkerFactory factory, FrontendClient frontendClient, TemporalService temporalService) {
        String QUEUE_NAME = "TASKS";
        Worker worker = factory.newWorker(QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(CustomerJourneyWorkflowImpl.class);
        worker.registerActivitiesImplementations(new ActivitiesImpl(frontendClient, temporalService));
        return worker;
    }

}
