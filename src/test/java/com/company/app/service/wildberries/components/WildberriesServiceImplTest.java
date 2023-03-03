package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import com.company.app.service.application.tools.api.DataExtractorService;
import com.company.app.service.application.tools.impl.DataExtractorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

public class WildberriesServiceImplTest {

	private static final String FILE_NAME = "wildberries/wildberries.properties";

	WildberriesServiceImpl wildberriesService;
	DataExtractorService dataExtractorService;
	WildberriesPriceExtractor wildberriesPriceExtractor;
	LotRepository lotRepository;
	WildberriesLotCreator wildberriesLotCreator;

	@Before
	public void init() {
		wildberriesService = new WildberriesServiceImpl();

		dataExtractorService = new DataExtractorServiceImpl();
		wildberriesPriceExtractor = new WildberriesPriceExtractor();
		lotRepository = Mockito.mock(LotRepository.class);

		wildberriesService.setDataExtractorService(dataExtractorService);
		wildberriesService.setWildberriesPriceExtractor(wildberriesPriceExtractor);
		wildberriesService.setLotRepository(lotRepository);

		wildberriesLotCreator = new WildberriesLotCreator();

		wildberriesPriceExtractor.setDataExtractorService(dataExtractorService);
	}

	@Test
	public void getDesiredLots() {
		Map<String, String> properties = dataExtractorService.getProperties(FILE_NAME);
		List<Lot> lots = wildberriesLotCreator.createFromProperties(properties);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesService.getDesiredLots();
		Assert.assertEquals(desiredLots.size(), 1);
	}
}