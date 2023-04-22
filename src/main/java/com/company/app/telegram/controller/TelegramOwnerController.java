package com.company.app.telegram.controller;

import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.service.api.ChatService;
import com.company.app.telegram.util.ChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/telegram/owner")
public class TelegramOwnerController {

	@Autowired
	ChatService chatService;

	/**
	 * пример запроса: http://localhost:8080/telegram/owner/getAllTelegramSettingsAsJson
	 */
	@GetMapping(value = "/getAllTelegramSettingsAsJson", produces = "application/json")
	public ResponseEntity<List<ChatDto>> getAllTelegramSettingsAsJson() {
		List<Chat> chatList = chatService.getAll();
		List<ChatDto> dtoList = ChatUtil.of(chatList);
		return ResponseEntity.ok(dtoList);
	}
}
