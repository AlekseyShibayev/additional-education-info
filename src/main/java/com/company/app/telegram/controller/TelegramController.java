package com.company.app.telegram.controller;

import com.company.app.telegram.component.api.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/telegram")
public class TelegramController {

	@Autowired
	private NotificationService notificationService;

	/**
	 * пример запроса: http://localhost:8080/telegram/say?message=hello
	 */
	@GetMapping(value = "/say", produces = "application/json")
	public void say(@RequestParam String message) {
		notificationService.eventNotification(message);
	}
}
