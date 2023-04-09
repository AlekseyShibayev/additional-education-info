package com.company.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * swagger: http://localhost:8080/swagger-ui.html
 * <p>
 * Тесты:
 * 1. JUnit
 * 2. Component test. С использованием SpringBootComponentScanStopper. Example:
 * {@link com.company.app.exchangerate.component.ExchangeRateFacadeTest}
 * 3. Full application test. Example:
 * {@link com.company.app.springboot.application.ApplicationTest}
 */
@Slf4j
//@EnableCaching
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ApplicationRunner runner() {
		return args -> log.debug("*******************  The app has been started.  *******************");
	}
}
