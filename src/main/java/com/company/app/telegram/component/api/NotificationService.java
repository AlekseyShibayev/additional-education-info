package com.company.app.telegram.component.api;

import org.springframework.stereotype.Component;

@Component
public interface NotificationService {

	void eventNotification(Object message);
}
