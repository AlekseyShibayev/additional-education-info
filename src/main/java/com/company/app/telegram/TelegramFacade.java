package com.company.app.telegram;

import com.company.app.telegram.component.api.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramFacade {

	@Autowired
	private TelegramService telegramService;

	public void write(Object message) {
		telegramService.write(message);
	}
}
