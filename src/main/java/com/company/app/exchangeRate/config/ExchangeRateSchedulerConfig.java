package com.company.app.exchangeRate.config;

import com.company.app.core.main.api.NotificationService;
import com.company.app.exchangeRate.ExchangeRateFacade;
import com.company.app.exchangeRate.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ExchangeRateSchedulerConfig {

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ExchangeRateFacade exchangeRateFacade;

	@Scheduled(fixedDelayString = "${exchangeRate.timeout}")
	public void writeExchange() {
		ExchangeRate exchangeRate = exchangeRateFacade.extract();
		notificationService.eventNotification(exchangeRate.getAliexpressExchangeRate() + " (курс али)");
	}
}
