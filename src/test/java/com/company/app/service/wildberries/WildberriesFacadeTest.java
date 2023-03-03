package com.company.app.service.wildberries;

import com.company.app.AbstractTest;
import com.company.app.entity.Lot;
import com.company.app.repository.LotRepository;
import com.company.app.service.application.tools.api.SerializationService;
import com.company.app.service.wildberries.components.WildberriesServiceImplTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

public class WildberriesFacadeTest extends AbstractTest {

	@Autowired
	private WildberriesFacade wildberriesFacade;
	@Autowired
	private SerializationService<Lot> serializationService;
	@MockBean
	private LotRepository lotRepository;

	@Test
	public void doMainLogicTest() {
		List<Lot> lots = serializationService.load(WildberriesServiceImplTest.FILE_NAME, Lot[].class);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);

		List<Lot> desiredLots = wildberriesFacade.getDesiredLots();
		Assert.assertEquals(desiredLots.size(), 1);
	}
}