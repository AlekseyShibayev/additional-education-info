package com.company.app.service.wildberries.components;

import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import com.company.app.service.application.tools.api.DataExtractorService;
import com.company.app.service.application.tools.api.SerializationService;
import com.company.app.service.application.tools.impl.DataExtractorServiceImpl;
import com.company.app.service.application.tools.impl.SerializationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class WildberriesServiceImplTest {

	public static final String FILE_NAME = "src/test/resources/wildberries/lot.json";

	WildberriesServiceImpl wildberriesService;
	DataExtractorService dataExtractorService;
	WildberriesPriceExtractor wildberriesPriceExtractor;
	LotRepository lotRepository;
	SerializationService<Lot> serializationService;

	@Before
	public void init() {
		wildberriesService = new WildberriesServiceImpl();

		dataExtractorService = new DataExtractorServiceImpl();
		wildberriesPriceExtractor = new WildberriesPriceExtractor();
		lotRepository = Mockito.mock(LotRepository.class);

		wildberriesService.setDataExtractorService(dataExtractorService);
		wildberriesService.setWildberriesPriceExtractor(wildberriesPriceExtractor);
		wildberriesService.setLotRepository(lotRepository);

		wildberriesPriceExtractor.setDataExtractorService(dataExtractorService);
		serializationService = new SerializationServiceImpl<>();
	}

	@Test
	public void getDesiredLots() {
		List<Lot> lots = serializationService.load(FILE_NAME, Lot.class);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesService.getDesiredLots();
		Assert.assertEquals(desiredLots.size(), 1);
	}
}