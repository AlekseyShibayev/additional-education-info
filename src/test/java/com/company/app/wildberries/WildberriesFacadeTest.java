package com.company.app.wildberries;

import com.company.app.ApplicationTest;
import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.wildberries.component.WildberriesServiceImplTest;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.util.List;

@Slf4j
public class WildberriesFacadeTest extends ApplicationTest {

	@Autowired
	private WildberriesFacade wildberriesFacade;
	@Autowired
	private JsonSerializationTool<Lot> serializationService;
	@MockBean
	private LotRepository lotRepository;

	@Test
	public void doMainLogicTest() {
		log.debug("Я заставляю поднять Spring Boot Test еще раз, потому что у меня @MockBean.");
		File file = new File(WildberriesServiceImplTest.FILE_NAME);
		List<Lot> lots = serializationService.load(file, Lot.class);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesFacade.getDesiredLots();
		Assert.assertEquals(desiredLots.size(), 1);
	}
}