package com.company.app.exchangeRate.scheduler;

import com.company.app.exchangeRate.ExchangeRateFacade;
import com.company.app.exchangeRate.entity.ExchangeRate;
import com.company.app.telegram.component.api.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
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
		String notification = exchangeRate.getAliexpressExchangeRate() + " (курс али)";
		if (log.isDebugEnabled()) {
			log.debug("Определён курс: [{}].", notification);
		}
		notificationService.eventNotification(notification);
	}
}
