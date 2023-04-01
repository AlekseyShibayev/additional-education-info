package com.company.app.core.main.api;

import org.springframework.stereotype.Component;

@Component
public interface NotificationService {

	void eventNotification(Object message);
}
