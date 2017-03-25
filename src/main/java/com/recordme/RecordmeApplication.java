package com.recordme;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.recordme.modules"})
@EnableTransactionManagement
public class RecordmeApplication{

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(RecordmeApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(RecordmeApplication.class, args);
	}


}
