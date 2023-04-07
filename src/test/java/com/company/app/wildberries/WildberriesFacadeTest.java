package com.company.app.wildberries;

import com.company.app.ApplicationTest;
import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.wildberries.component.WildberriesServiceImplTest;
import com.company.app.wildberries.entity.Lot;
import com.company.app.wildberries.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.util.List;

@Slf4j
class WildberriesFacadeTest extends ApplicationTest {

	@Autowired
	private WildberriesFacade wildberriesFacade;
	@Autowired
	private JsonSerializationTool<Lot> serializationService;
	@MockBean
	private LotRepository lotRepository;

	@Test
	void doMainLogicTest() {
		File file = new File(WildberriesServiceImplTest.FILE_NAME);
		List<Lot> lots = serializationService.load(file, Lot.class);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesFacade.getDesiredLots();
		Assertions.assertEquals(1, desiredLots.size());
	}
}