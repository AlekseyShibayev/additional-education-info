package com.company.app.service.wildberries.components;

import java.util.List;

public class WildberriesURLCreator {

	private static final String URL_BONE = "https://card.wb.ru/cards/detail?spp=0&regions=80,38,4,83,33,68,30,86,40,1,22,31,48,110&pricemarginCoeff=1.0&reg=0&appType=1&emp=0&locale=ru&lang=ru&curr=rub&couponsGeo=3,6,19,21,8&dest=-3827446&nm=";

	public static String getUrlForPriceSearch(String id) {
		return URL_BONE + id;
	}

	public static String getUrlForPriceSearch(List<String> ids) {
		String unitedString = "";
		for (String id : ids) {
			unitedString = unitedString + id + ";";
		}
		return URL_BONE + unitedString;
	}
}
