package com.company.app.service.wildberries;

import com.company.app.entity.Lot;
import com.company.app.service.wildberries.components.WildberriesServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
public class WildberriesFacade {

	@Autowired
	private WildberriesServiceImpl wildberriesService;

	public List<Lot> getDesiredLots() {
		return wildberriesService.getDesiredLots();
	}
}
