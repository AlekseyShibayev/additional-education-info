package com.company.app.services.telegram.api;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface TelegramBotService {

	Map<Long, String> getChats();
}
