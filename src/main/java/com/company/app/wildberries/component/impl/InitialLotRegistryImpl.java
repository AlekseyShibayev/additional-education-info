package com.company.app.wildberries.component.impl;

import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.wildberries.component.api.InitialLotRegistry;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitialLotRegistryImpl implements InitialLotRegistry {

	@Value("classpath:wildberries/init_lot.json")
	private Resource resource;

	@Autowired
	private LotRepository lotRepository;
	@Autowired
	private JsonSerializationTool<Lot> jsonSerializationTool;

	@SneakyThrows
	@PostConstruct
	public void init() {
		List<Lot> lots = jsonSerializationTool.load(resource, Lot.class);
		lotRepository.saveAll(lots);
	}
}
