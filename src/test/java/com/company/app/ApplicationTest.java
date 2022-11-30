package com.company.app;

import com.company.app.services.api.NotificationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationTest extends AbstractTest {

//	@Autowired
	private NotificationService notificationService;

//	@Test
	public void smokeTest() {
		notificationService.eventNotification("Тестовое приложение поднялось.");
		System.out.println("Тестовое приложение поднялось.");
	}
}