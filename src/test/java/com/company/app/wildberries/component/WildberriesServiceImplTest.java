package com.company.app.wildberries.component;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.core.tool.impl.DataExtractorToolImpl;
import com.company.app.core.tool.impl.JsonSerializationToolImpl;
import com.company.app.wildberries.component.impl.WildberriesPriceExtractorImpl;
import com.company.app.wildberries.component.impl.WildberriesServiceImpl;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

public class WildberriesServiceImplTest {

	public static final String FILE_NAME = "src/test/resources/wildberries/lot.json";

	WildberriesServiceImpl wildberriesService;
	DataExtractorTool dataExtractorService;
	WildberriesPriceExtractorImpl wildberriesPriceExtractor;
	LotRepository lotRepository;
	JsonSerializationTool<Lot> serializationService;

	@Before
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
	public void getDesiredLots() {
		List<Lot> lots = serializationService.load(new File(FILE_NAME), Lot.class);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesService.getDesiredLots();
		Assert.assertEquals(1, desiredLots.size());
	}
}