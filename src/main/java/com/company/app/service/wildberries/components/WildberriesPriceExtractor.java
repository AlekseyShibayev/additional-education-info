package com.company.app.service.wildberries.components;

import com.company.app.service.tools.impl.DataExtractorServiceImpl;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class WildberriesPriceExtractor {

	@Autowired
	private DataExtractorServiceImpl dataExtractorService;

	public String extract(String jsonResponse, String id) {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		JSONArray products = dataExtractorService.getJsonArray(jsonObject, "products");
		return getPrice(id, products);
	}

	private String getPrice(String id, JSONArray products) {
		for (Object object : products) {
			JSONObject product = (JSONObject) object;
			Integer productId = product.getInt("id");
			if (productId.equals(Integer.valueOf(id))) {
				return String.valueOf(product.getInt("salePriceU"));
			}
		}
		return "";
	}
}
