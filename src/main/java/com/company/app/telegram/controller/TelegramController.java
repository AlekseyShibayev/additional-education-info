package com.company.app.telegram.controller;

import com.company.app.telegram.component.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TelegramController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping(value = "/")
	public void interceptAll(@RequestBody Map<String, Object> lookupRequestObject) {
		notificationService.eventNotification(lookupRequestObject.toString());
	}

	@GetMapping(value = "/bot", produces = "application/json")
	public void writeBot(@RequestParam String message) {
		notificationService.eventNotification(message);
	}
}
