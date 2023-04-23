package com.company.app.wildberries.util;

import com.company.app.wildberries.entity.Lot;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WBUtilsTest {

	@Test
	void getUrlForPriceSearch() {
		String urlForPriceSearch = WBUtils.getUrlForPriceSearch(createLots());
		String manualCreated = WBUtils.URL_BONE + "43409221;" + "15694225;";
		Assertions.assertEquals(urlForPriceSearch, manualCreated);
	}

	private List<Lot> createLots() {
		return ImmutableList.<Lot>builder()
				.add(Lot.builder().article("43409221").desiredPrice("1500").discount("0.19").build())
				.add(Lot.builder().article("43409221").desiredPrice("1500").discount("0.19").build())
				.add(Lot.builder().article("43409221").desiredPrice("1500").discount("0.19").build())
				.add(Lot.builder().article("15694225").desiredPrice("5500").discount("0.17").build())
				.add(Lot.builder().article("15694225").desiredPrice("5500").discount("0.17").build())
				.build();
	}
}