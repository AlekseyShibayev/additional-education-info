package com.company.app.core.service.impl;

import com.company.app.core.service.api.NotificationService;
import com.company.app.telegram.TelegramFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private TelegramFacade telegramFacade;

	@Override
	public void notify(Object message) {
		telegramFacade.write(message);
	}
}
