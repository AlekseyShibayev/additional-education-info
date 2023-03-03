package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WildberriesLotCreator {

	public List<Lot> createFromProperties(Map<String, String> properties) {
		List<Lot> lots = new ArrayList<>();
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			lots.add(Lot.builder()
					.name(entry.getKey())
					.price(entry.getValue())
					.build()
			);
		}
		return lots;
	}
}
