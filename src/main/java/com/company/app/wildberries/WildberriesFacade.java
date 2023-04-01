package com.company.app.wildberries;

import com.company.app.core.aop.logging.PerformanceLogAnnotation;
import com.company.app.wildberries.component.WildberriesServiceImpl;
import com.company.app.wildberries.entity.Lot;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
public class WildberriesFacade {

	@Autowired
	private WildberriesServiceImpl wildberriesService;

	@PerformanceLogAnnotation
	public List<Lot> getDesiredLots() {
		return wildberriesService.getDesiredLots();
	}
}
