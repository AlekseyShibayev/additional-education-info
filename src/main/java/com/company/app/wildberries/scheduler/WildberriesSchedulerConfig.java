package com.company.app.wildberries.scheduler;

import com.company.app.telegram.component.api.NotificationService;
import com.company.app.wildberries.WildberriesFacade;
import com.company.app.wildberries.component.WildberriesURLCreator;
import com.company.app.wildberries.entity.Lot;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
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

		if (log.isDebugEnabled()) {
			log.debug("Определены желаемые лоты вб, в количестве: [{}].", desiredLots.size());
		}

		if (CollectionHelper.isNotEmpty(desiredLots)) {
			desiredLots.forEach(lot -> notificationService.eventNotification(WildberriesURLCreator.getUrlForResponse(lot.getName())));
		}
	}
}
