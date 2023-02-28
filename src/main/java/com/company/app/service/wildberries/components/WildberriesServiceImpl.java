package com.company.app.service.wildberries.components;

import com.company.app.service.other.api.NotificationService;
import com.company.app.service.tools.impl.DataExtractorServiceImpl;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Setter
public class WildberriesServiceImpl {

	private static final String WILDBERRIES_PROPERTIES = "wildberries.properties";

	@Autowired
	private WildberriesPriceExtractor wildberriesPriceExtractor;
	@Autowired
	private DataExtractorServiceImpl dataExtractorService;
	@Autowired
	private NotificationService notificationService;

	public void doMainLogic() {
		Map<String, String> properties = dataExtractorService.getProperties(WILDBERRIES_PROPERTIES);
		String url = WildberriesURLCreator.getUrlForPriceSearch(Lists.newArrayList(properties.keySet()));
		String htmlResponse = dataExtractorService.getHtmlResponse(url);

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			String id = entry.getKey();
			String price = entry.getValue() + "00";
			String currentPrice = wildberriesPriceExtractor.extract(htmlResponse, id);
			if (Integer.parseInt(currentPrice) < Integer.parseInt(price)) {
				notificationService.eventNotification(id);
			}
		}
	}
}
