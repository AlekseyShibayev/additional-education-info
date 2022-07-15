package com.company.app.services.api;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ChatService {

    Map<Long, String> getChats();
}
