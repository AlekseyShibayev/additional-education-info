package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import com.company.app.service.application.tools.impl.DataExtractorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class WildberriesBinderTest {

	private static final String TEST_STRING = "2 59509066=500;61939396=500";

	WildberriesBinder wildberriesBinder;

	@Before
	public void init() {
		wildberriesBinder = new WildberriesBinder();
		wildberriesBinder.setWildberriesLotCreator(new WildberriesLotCreator());
	}

	@Test
	public void bind() {
		List<Lot> lots = wildberriesBinder.createLots(TEST_STRING);
		Assert.assertEquals(lots.size(), 2);
	}
}