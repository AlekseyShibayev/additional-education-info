package com.company.app.service.wildberries;

import com.company.app.service.wildberries.components.WildberriesServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WildberriesFacadeTest {

	private WildberriesFacade wildberriesFacade;
	private WildberriesServiceImpl wildberriesService;

	@Before
	public void init() {
		wildberriesFacade = new WildberriesFacade();
		wildberriesFacade.setWildberriesService(new WildberriesServiceImpl());
	}

	@Ignore // todo
	@Test
	public void doMainLogic() {
		wildberriesFacade.doMainLogic();
	}
}