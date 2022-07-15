package com.company.app.services.impl;

import com.company.app.services.api.ChatService;
import com.company.app.services.api.DataExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatServiceImpl implements ChatService {

    private static final String CHAT_PROPERTIES = "chat.properties";

    @Autowired
    private DataExtractorService dataExtractorService;

    @Override
    public Map<Long, String> getChats() {
        Map<Long, String> result = new HashMap<>();
        dataExtractorService.getProperties(CHAT_PROPERTIES)
                .forEach((key, value) -> result.put(Long.parseLong(key), value));
        return result;
    }
}
