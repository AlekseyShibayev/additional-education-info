package com.company.app.wildberries.component;

import com.company.app.core.tools.api.SerializationService;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WildberriesBinder {

	@Autowired
	LotRepository lotRepository;
	@Autowired
	SerializationService<Lot> serializationService;

	public void bind(String string) {
		string = string.substring(3);
		List<Lot> lots = serializationService.load(string, Lot.class);
		if (log.isDebugEnabled()) {
			log.debug("Пробую добавить лотов [{}].", lots.size());
		}
		lotRepository.saveAll(lots);
	}
}
