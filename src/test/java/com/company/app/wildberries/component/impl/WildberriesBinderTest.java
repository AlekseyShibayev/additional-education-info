package com.company.app.wildberries.component.impl;

import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.springboot.application.SpringBootApplicationTest;
import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

class WildberriesBinderTest extends SpringBootApplicationTest {

	@Autowired
	WildberriesBinderImpl wildberriesBinder;
	@Autowired
	JsonSerializationTool<LotDto> serializationService;
	@Autowired
	LotRepository lotRepository;

	@Test
	void bind() {
		List<Lot> before = lotRepository.findAll();

		LotDto lot = LotDto.builder()
				.name("17010096")
				.price("900")
				.discount("0.11")
				.build();

		String rightPart = "WB " + serializationService.asString(Collections.singletonList(lot));
		wildberriesBinder.bind(rightPart);

		List<Lot> after = lotRepository.findAll();

		Assertions.assertEquals(1, after.size() - before.size());
	}
}