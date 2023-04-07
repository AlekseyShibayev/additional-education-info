package com.company.app.wildberries.component.impl;

import com.company.app.core.tools.api.SerializationService;
import com.company.app.wildberries.component.api.WildberriesBinder;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WildberriesBinderImpl implements WildberriesBinder {

	private static final String TYPE = "WB";

	@Autowired
	LotRepository lotRepository;
	@Autowired
	SerializationService<Lot> serializationService;

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void bind(String string) {
		string = string.substring(3);
		List<Lot> lots = serializationService.load(string, Lot.class);
		if (log.isDebugEnabled()) {
			log.debug("Пробую добавить лотов [{}].", lots.size());
		}
		lotRepository.saveAll(lots);
	}
}
