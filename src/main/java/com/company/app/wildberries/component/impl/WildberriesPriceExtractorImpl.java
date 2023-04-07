package com.company.app.wildberries.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.wildberries.component.api.WildberriesPriceExtractor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter
@Component
public class WildberriesPriceExtractorImpl implements WildberriesPriceExtractor {

	@Autowired
	private DataExtractorTool dataExtractorService;

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
