package com.studyhub.sth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRedisRepositories(basePackages = "com.studyhub.sth.infra.redis")
@EnableJpaRepositories(basePackages = "com.studyhub.sth.domain.repositories")
public class SthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SthApplication.class, args);
	}

}
