package com.company.app.application;

import com.company.app.AbstractTest;
import com.company.app.telegram.component.api.NotificationService;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.repository.HistoryRepository;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApplicationTest extends AbstractTest {

	@LocalServerPort
	private Integer port;

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private HistoryRepository historyRepository;

	@Test
	public void historyRepositorySmokeTest() {
		historyRepository.deleteAll();
		historyRepository.save(History.builder()
				.message("0. Успешное сохранение в бд.")
				.build());
		Assert.assertEquals(1, Lists.newArrayList(historyRepository.findAll()).size());
	}

	@Test
	public void notificationServiceSmokeTest() {
		notificationService.eventNotification("1. Тестовое приложение поднялось.");
		List<History> all = historyRepository.findAll();
		Assert.assertNotNull(all);
	}

	@Test
	public void controllerSmokeTest() {
		String message = String.format("2. На порту %s успешно поднялось тестовое приложение.", port);
		ResponseEntity<Object> entity = testRestTemplate.getForEntity(String.format("/telegram/say?message=%s", message), Object.class);
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}