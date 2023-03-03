package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import com.company.app.service.application.tools.api.DataExtractorService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Setter
public class WildberriesServiceImpl {

	private static final String WILDBERRIES_PROPERTIES = "wildberries.properties";

	@Autowired
	private WildberriesPriceExtractor wildberriesPriceExtractor;
	@Autowired
	private DataExtractorService dataExtractorService;
	@Autowired
	private LotRepository lotRepository;
	@Autowired
	private WildberriesLotCreator wildberriesLotCreator;

	@PostConstruct
	void init() {
		Map<String, String> properties = dataExtractorService.getProperties(WILDBERRIES_PROPERTIES);
		List<Lot> lots = wildberriesLotCreator.createFromProperties(properties);
		lotRepository.saveAll(lots);
	}

	public List<Lot> getDesiredLots() {
		List<Lot> lots = lotRepository.findAll();
		String url = WildberriesURLCreator.getUrlForPriceSearch(lots);
		String htmlResponse = dataExtractorService.getHtmlResponse(url);
		return lots.stream().filter(lot -> isDesireLot(htmlResponse, lot)).collect(Collectors.toList());
	}

	private boolean isDesireLot(String htmlResponse, Lot lot) {
		String currentPrice = wildberriesPriceExtractor.extract(htmlResponse, lot.getName());
		return Integer.parseInt(currentPrice) < Integer.parseInt(lot.getPrice() + "00");
	}
}
