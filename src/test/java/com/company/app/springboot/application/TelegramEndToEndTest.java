package com.company.app.springboot.application;

import com.company.app.telegram.controller.ChatController;
import com.company.app.telegram.controller.TelegramController;
import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.service.api.ChatService;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

class TelegramEndToEndTest extends ApplicationSpringBootTestContext {

	@Autowired
	TelegramController telegramController;
	@Autowired
	ChatController chatController;
	@Autowired
	ChatService chatService;

	@Test
	void telegram_chat_history_test() {
		ResponseEntity<Long> chatId = chatController.create(ChatDto.builder().chatId(653606407L).role("Admin").build());
		telegramController.say("test_text");

		Chat chat = chatService.read(chatId.getBody());
		List<History> historyList = chat.getHistoryList();

		Assertions.assertNotNull(historyList);
		Assertions.assertEquals(1, historyList.size());
		Assertions.assertEquals("test_text", historyList.get(0).getMessage());
	}

	@Test
	void chatController_create_delete_read_test() {
		ResponseEntity<Long> chatId = chatController.create(ChatDto.builder().chatId(1974712519L).role("User").build());
		Long id = chatId.getBody();
		chatController.delete(id);
		Assertions.assertThrows(ObjectNotFoundException.class, () -> chatController.read(id));
	}
}
