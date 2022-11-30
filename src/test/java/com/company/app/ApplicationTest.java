package com.company.app;

import com.company.app.services.api.NotificationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class ApplicationTest extends AbstractTest {

	@LocalServerPort
	private Integer port;

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void smokeTest() {
		notificationService.eventNotification("1. Тестовое приложение поднялось.");
		System.out.println("Тестовое приложение поднялось.");
	}

	@Test
	public void accessApplication() {
		String message = String.format("2. На порту %s успешно поднялось тестовое приложение.", port);
		testRestTemplate.getForEntity(String.format("/bot?message=%s", message), Object.class);
	}
}