package com.company.app.service.other.api;

import org.springframework.stereotype.Component;

@Component
public interface NotificationService {

	void eventNotification(Object message);
}
