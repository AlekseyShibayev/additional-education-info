package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import com.company.app.service.application.tools.api.DataExtractorService;
import com.company.app.service.application.tools.api.SerializationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Setter
public class WildberriesServiceImpl {

	private static final String FILE_NAME = "src/main/resources/wildberries.lot.json";

	@Autowired
	private WildberriesPriceExtractor wildberriesPriceExtractor;
	@Autowired
	private DataExtractorService dataExtractorService;
	@Autowired
	private LotRepository lotRepository;
	@Autowired
	private SerializationService<Lot> serializationService;

	@PostConstruct
	void init() {
		List<Lot> lots = serializationService.load(FILE_NAME, Lot.class);
		lotRepository.saveAll(lots);
	}

	public List<Lot> getDesiredLots() {
		List<Lot> lots = lotRepository.findAll();
		String url = WildberriesURLCreator.getUrlForPriceSearch(lots);
		String htmlResponse = dataExtractorService.getHtmlResponse(url);
		lots = lots.stream().filter(lot -> isDesireLot(htmlResponse, lot)).collect(Collectors.toList());
		return lots;
	}

	private boolean isDesireLot(String htmlResponse, Lot lot) {
		String currentPriceString = wildberriesPriceExtractor.extract(htmlResponse, lot.getName());
		BigDecimal currentPrice = getCurrentPrice(lot, currentPriceString);
		BigDecimal desiredPrice = new BigDecimal(lot.getPrice() + "00.00");
		int i = desiredPrice.compareTo(currentPrice);
		return i > 0;
	}

	private BigDecimal getCurrentPrice(Lot lot, String currentPriceString) {
		String discount = lot.getDiscount();
		BigDecimal currentPrice = new BigDecimal(currentPriceString);
		BigDecimal multiply = currentPrice.multiply(new BigDecimal(discount));
		currentPrice = currentPrice.subtract(multiply);
		return currentPrice;
	}
}
