package com.company.app.config;

import com.company.app.services.api.ExchangeExtractorService;
import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {

	@Autowired
	NotificationService notificationService;

	@Autowired
	private ExchangeExtractorService exchangeExtractorService;

	@Scheduled(fixedDelay = 300000)
	public void temp() {
		notificationService.eventNotification(exchangeExtractorService.extractCurse());
	}
}
