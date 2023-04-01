package com.company.app.wildberries.config;

import com.company.app.core.main.api.NotificationService;
import com.company.app.wildberries.WildberriesFacade;
import com.company.app.wildberries.component.WildberriesURLCreator;
import com.company.app.wildberries.entity.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class WildberriesSchedulerConfig {

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private WildberriesFacade wildberriesFacade;

	@Scheduled(fixedDelayString = "${wildberries.timeout}")
	public void searchWildberriesLots() {
		List<Lot> desiredLots = wildberriesFacade.getDesiredLots();
		if (!CollectionUtils.isEmpty(desiredLots)) {
			desiredLots.forEach(lot -> notificationService.eventNotification(WildberriesURLCreator.getUrlForResponse(lot.getName())));
		}
	}
}
