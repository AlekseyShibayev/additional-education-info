package com.company.app.wildberries.util;

import com.company.app.wildberries.entity.Lot;

import java.util.List;

public class WBUtils {

	static final String URL_BONE = "https://card.wb.ru/cards/detail?spp=0&regions=80,38,4,83,33,68,30,86,40,1,22,31,48,110&pricemarginCoeff=1.0&reg=0&appType=1&emp=0&locale=ru&lang=ru&curr=rub&couponsGeo=3,6,19,21,8&dest=-3827446&nm=";
	static final String URL_RESPONSE = "https://www.wildberries.ru/catalog/%s/detail.aspx";

	private WBUtils() {
		throw new UnsupportedOperationException();
	}

	public static String getUrlForPriceSearch(String id) {
		return URL_BONE + id;
	}

	public static String getUrlForPriceSearch(List<Lot> lots) {
		StringBuilder stringBuilder = new StringBuilder();

		lots.stream()
				.map(Lot::getName)
				.distinct()
				.forEach(name -> stringBuilder.append(name).append(";"));

		return getUrlForPriceSearch(stringBuilder.toString());
	}

	public static String getUrlForResponse(String id) {
		return String.format(URL_RESPONSE, id);
	}
}
