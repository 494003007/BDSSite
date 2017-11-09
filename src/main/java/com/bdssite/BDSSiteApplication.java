package com.bdssite;

import com.bdssite.modules.common.repository.ExtJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.bdssite.modules"},repositoryFactoryBeanClass = ExtJpaRepositoryFactoryBean.class)
@EnableTransactionManagement
public class BDSSiteApplication {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(RecordmeApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BDSSiteApplication.class, args);
	}
	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

}
