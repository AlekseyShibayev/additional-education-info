package com.company.app.wildberries.component;

import com.company.app.wildberries.entity.Lot;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WildberriesURLCreatorTest {

	@Test
	public void getUrlForPriceSearch() {
		String urlForPriceSearch = WildberriesURLCreator.getUrlForPriceSearch(createLots());
		String manualCreated = WildberriesURLCreator.URL_BONE + "43409221;" + "15694225;";
		Assert.assertEquals(urlForPriceSearch, manualCreated);
	}

	private List<Lot> createLots() {
		return ImmutableList.<Lot>builder()
				.add(Lot.builder().name("43409221").price("1500").discount("0.19").build())
				.add(Lot.builder().name("43409221").price("1500").discount("0.19").build())
				.add(Lot.builder().name("43409221").price("1500").discount("0.19").build())
				.add(Lot.builder().name("15694225").price("5500").discount("0.17").build())
				.add(Lot.builder().name("15694225").price("5500").discount("0.17").build())
				.build();
	}
}