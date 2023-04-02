package com.company.app.wildberries.component;

import com.company.app.AbstractTest;
import com.company.app.core.tools.api.SerializationService;
import com.company.app.wildberries.dto.LotDto;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class WildberriesBinderTest extends AbstractTest {

	@Autowired
	WildberriesBinder wildberriesBinder;
	@Autowired
	SerializationService<LotDto> serializationService;
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

		Assert.assertEquals(1, after.size() - before.size());
	}
}