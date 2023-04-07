package com.company.app.wildberries.component;

import com.company.app.ApplicationTest;
import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.wildberries.component.impl.WildberriesBinderImpl;
import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class WildberriesBinderTest extends ApplicationTest {

	@Autowired
	WildberriesBinderImpl wildberriesBinder;
	@Autowired
	JsonSerializationTool<LotDto> serializationService;
	@Autowired
	LotRepository lotRepository;

	@Test
	public void bind() {
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