package com.company.app.wildberries.component.impl;

import com.company.app.core.aop.logging.util.LogUtils;
import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.wildberries.component.api.WildberriesPriceExtractor;
import com.company.app.wildberries.component.api.WildberriesService;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import com.company.app.wildberries.util.WBUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Component
public class WildberriesServiceImpl implements WildberriesService {

	@Autowired
	private WildberriesPriceExtractor wildberriesPriceExtractor;
	@Autowired
	private DataExtractorTool dataExtractorTool;
	@Autowired
	private LotRepository lotRepository;

	public List<Lot> getDesiredLots() {
		List<Lot> lots = lotRepository.findAll();
		String url = WBUtils.getUrlForPriceSearch(lots);
		String htmlResponse = dataExtractorTool.getHtmlResponse(url);
		return lots.stream()
				.filter(lot -> isDesireLot(htmlResponse, lot))
				.collect(Collectors.toList());
	}

	private boolean isDesireLot(String htmlResponse, Lot lot) {
		try {
			String currentPriceString = wildberriesPriceExtractor.extract(htmlResponse, lot.getArticle());
			BigDecimal currentPrice = getCurrentPrice(lot, currentPriceString);
			BigDecimal desiredPrice = new BigDecimal(lot.getDesiredPrice() + "00.00");
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
