package com.company.app.service.application.main.api;

import org.springframework.stereotype.Component;

@Component
public interface NotificationService {

	void eventNotification(Object message);
}
