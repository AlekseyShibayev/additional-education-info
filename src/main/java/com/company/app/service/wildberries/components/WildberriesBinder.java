package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Setter
public class WildberriesBinder {

	@Autowired
	LotRepository lotRepository;
	@Autowired
	WildberriesLotCreator wildberriesLotCreator;

	public void bind(String string) {
		List<Lot> lots = createLots(string);
		lotRepository.saveAll(lots);
	}

	List<Lot> createLots(String string) {
		String substring = string.substring(3);
		Map<String, String> map = getStringStringMap(substring);
		return wildberriesLotCreator.createFromProperties(map);
	}

	private static Map<String, String> getStringStringMap(String substring) {
		String[] split = substring.split(";");
		Map<String, String> map = new HashMap<>();
		for (String s : split) {
			String[] innerSplit = s.split("=");
			map.put(innerSplit[0], innerSplit[1]);
		}
		return map;
	}
}
