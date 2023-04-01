package com.company.app.wildberries.component;

import com.company.app.wildberries.entity.Lot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class WildberriesBinderTest {

	private static final String TEST_STRING = "$2 59509066=500;61939396=500";

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