package com.company.app.telegram.component.api;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ChatService {

	Map<Long, String> getChats();
}
