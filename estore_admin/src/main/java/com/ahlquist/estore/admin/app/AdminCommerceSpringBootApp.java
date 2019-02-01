package com.ahlquist.estore.admin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "com.ahlquist.estore.repositories")
@SpringBootApplication(scanBasePackages = { "com.ahlquist.estore.admin.controller", "com.ahlquist.estore.model",
		"com.ahlquist.estore.services", "com.ahlquist.estore.services" })
@ImportResource({ "classpath*:spring-config.xml" })
@EnableTransactionManagement
public class AdminCommerceSpringBootApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AdminCommerceSpringBootApp.class, args);
	}

}