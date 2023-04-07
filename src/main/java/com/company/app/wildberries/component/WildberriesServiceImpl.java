package com.company.app.wildberries.component;

import com.company.app.core.tools.api.DataExtractorService;
import com.company.app.core.tools.api.SerializationService;
import com.company.app.core.aop.logging.util.LogUtils;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Component
public class WildberriesServiceImpl {

	@Value("classpath:wildberries_lot.json")
	private Resource resource;

	@Autowired
	private WildberriesPriceExtractor wildberriesPriceExtractor;
	@Autowired
	private DataExtractorService dataExtractorService;
	@Autowired
	private LotRepository lotRepository;
	@Autowired
	private SerializationService<Lot> serializationService;

	@SneakyThrows
	@PostConstruct
	void init() {
		List<Lot> lots = serializationService.load(resource, Lot.class);
		lotRepository.saveAll(lots);
	}

	public List<Lot> getDesiredLots() {
		List<Lot> lots = lotRepository.findAll();
		String url = WildberriesURLCreator.getUrlForPriceSearch(lots);
		String htmlResponse = dataExtractorService.getHtmlResponse(url);
		return lots.stream()
				.filter(lot -> isDesireLot(htmlResponse, lot))
				.collect(Collectors.toList());
	}

	private boolean isDesireLot(String htmlResponse, Lot lot) {
		try {
			String currentPriceString = wildberriesPriceExtractor.extract(htmlResponse, lot.getName());
			BigDecimal currentPrice = getCurrentPrice(lot, currentPriceString);
			BigDecimal desiredPrice = new BigDecimal(lot.getPrice() + "00.00");
			int i = desiredPrice.compareTo(currentPrice);
			return i > 0;
		} catch (Exception e) {
			LogUtils.doExceptionLog(e, "проблема с " + lot.toString());
			return false;
		}
	}

	private BigDecimal getCurrentPrice(Lot lot, String currentPriceString) {
		String discount = lot.getDiscount();
		BigDecimal currentPrice = new BigDecimal(currentPriceString);
		BigDecimal multiply = currentPrice.multiply(new BigDecimal(discount));
		currentPrice = currentPrice.subtract(multiply);
		return currentPrice;
	}
}
