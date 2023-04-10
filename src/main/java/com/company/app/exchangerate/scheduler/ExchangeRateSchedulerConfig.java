package com.company.app.exchangerate.scheduler;

import com.company.app.exchangerate.component.ExchangeRateFacade;
import com.company.app.exchangerate.entity.ExchangeRate;
import com.company.app.telegram.component.TelegramFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ExchangeRateSchedulerConfig {

	@Autowired
	private TelegramFacade telegramFacade;
	@Autowired
	private ExchangeRateFacade exchangeRateFacade;

	@Scheduled(fixedDelayString = "${exchangeRate.timeout}")
	public void writeExchange() {
		ExchangeRate exchangeRate = exchangeRateFacade.extract();
		String notification = exchangeRate.getAliexpressExchangeRate() + " (курс али)";
		if (log.isDebugEnabled()) {
			log.debug("Определён курс: [{}].", notification);
		}
		telegramFacade.write(notification);
	}
}
