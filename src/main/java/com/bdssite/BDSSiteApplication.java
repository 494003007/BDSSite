package com.bdssite;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.bdssite.modules"})
@EnableTransactionManagement
public class BDSSiteApplication {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(RecordmeApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BDSSiteApplication.class, args);
	}


}
