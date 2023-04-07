package com.company.app.wildberries;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.wildberries.component.api.WildberriesService;
import com.company.app.wildberries.entity.Lot;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Component
public class WildberriesFacade {

	@Autowired
	private WildberriesService wildberriesService;

	@PerformanceLogAnnotation
	public List<Lot> getDesiredLots() {
		return wildberriesService.getDesiredLots();
	}
}
