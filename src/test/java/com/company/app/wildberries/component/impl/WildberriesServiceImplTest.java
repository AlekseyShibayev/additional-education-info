package com.company.app.wildberries.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.core.tool.impl.DataExtractorToolImpl;
import com.company.app.core.tool.impl.JsonSerializationToolImpl;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

class WildberriesServiceImplTest {

	private static final String FILE_NAME = "src/test/resources/wildberries/lot.json";

	WildberriesServiceImpl wildberriesService;
	DataExtractorTool dataExtractorService;
	WildberriesPriceExtractorImpl wildberriesPriceExtractor;
	LotRepository lotRepository;
	JsonSerializationTool<Lot> serializationService;

	@BeforeEach
	public void init() {
		wildberriesService = new WildberriesServiceImpl();

		dataExtractorService = new DataExtractorToolImpl();
		wildberriesPriceExtractor = new WildberriesPriceExtractorImpl();
		lotRepository = Mockito.mock(LotRepository.class);

		wildberriesService.setDataExtractorTool(dataExtractorService);
		wildberriesService.setWildberriesPriceExtractor(wildberriesPriceExtractor);
		wildberriesService.setLotRepository(lotRepository);

		wildberriesPriceExtractor.setDataExtractorService(dataExtractorService);
		serializationService = new JsonSerializationToolImpl<>();
	}

	@Test
	void getDesiredLots() {
		List<Lot> lots = serializationService.load(new File(FILE_NAME), Lot.class);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesService.getDesiredLots();
		Assertions.assertEquals(1, desiredLots.size());
	}
}