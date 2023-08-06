package com.mitacs.customerjourney;

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
