package com.company.app.springboot.application;

import com.company.app.telegram.component.TelegramFacade;
import com.company.app.telegram.component.api.ChatRegistry;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.repository.HistoryRepository;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

class SmokeTest extends ApplicationSpringBootTestContext {

	@LocalServerPort
	Integer port;
	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	TelegramFacade telegramFacade;
	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	ChatRegistry chatRegistry;

	@Test
	void historyRepositorySmokeTest() {
		historyRepository.deleteAll();

		List<Chat> chatList = chatRegistry.getAll();

		historyRepository.save(History.builder()
				.chat(chatList.get(0))
				.message("0. Успешное сохранение в бд.")
				.build());

		Assertions.assertEquals(1, Lists.newArrayList(historyRepository.findAll()).size());
	}

	@Test
	void notificationServiceSmokeTest() {
		telegramFacade.write("1. Тестовое приложение поднялось.");

		List<History> all = historyRepository.findAll();
		Assertions.assertNotNull(all);
	}

	@Test
	void controllerSmokeTest() {
		String message = String.format("2. На порту %s успешно поднялось тестовое приложение.", port);
		ResponseEntity<Object> entity = testRestTemplate.getForEntity(String.format("/telegram/say?message=%s", message), Object.class);
		Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}