package com.mitacs.customerjourney;

import com.mitacs.customerjourney.service.FrontendClient;
import com.mitacs.customerjourney.temporal.ActivitiesImpl;
import com.mitacs.customerjourney.temporal.CustomerJourneyWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CustomerJourneyApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CustomerJourneyApplication.class, args);
		WorkerFactory factory = applicationContext.getBean(WorkerFactory.class);
		factory.start();
	}

}
