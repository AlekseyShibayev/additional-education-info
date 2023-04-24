package com.company.app.springboot.application.telegram;

import com.company.app.springboot.application.ApplicationSpringBootTestContext;
import com.company.app.telegram.component.data.BinderContainer;
import com.company.app.telegram.component.impl.TelegramBinder;
import com.company.app.telegram.controller.ChatController;
import com.company.app.telegram.controller.TelegramController;
import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.entity.UserInfo;
import com.company.app.telegram.repository.ChatRepository;
import com.company.app.telegram.service.api.ChatService;
import com.company.app.telegram.util.ChatUtil;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
	@Autowired
	TelegramBinder telegramBinder;
	@Autowired
	ChatRepository chatRepository;

	@BeforeEach
	void init() {
		chatRepository.deleteAll();
	}

	@Test
	void telegram_chat_history_test() {
		ResponseEntity<Long> chatId = chatController.create(ChatDto.builder()
				.chatId(653606407L)
				.enableNotifications(true)
				.build());
		telegramController.say("test_text");

		Chat chat = chatService.read(chatId.getBody());
		List<History> historyList = chat.getHistoryList();

		Assertions.assertNotNull(historyList);
		Assertions.assertEquals(1, historyList.size());
		Assertions.assertEquals("test_text", historyList.get(0).getMessage());
	}

	@Test
	void chatController_crud_test() {
		ChatDto chatDto = ChatDto.builder().chatId(653606407L).build();
		Long id = chatController.create(chatDto).getBody();
		Assertions.assertEquals(1, chatRepository.findAll().size());

		chatDto.setUserInfo(UserInfo.builder().role("Test_Role").build());
		chatController.update(id, chatDto);
		Assertions.assertEquals(1, chatRepository.findAll().size());

		Chat after = chatController.read(id).getBody();
		Assertions.assertEquals("Test_Role", after.getUserInfo().getRole());

		chatController.delete(id);
		Assertions.assertEquals(0, chatRepository.findAll().size());
		Assertions.assertThrows(ObjectNotFoundException.class, () -> chatController.read(id));
	}

	@Test
	void history_list_is_empty_if_enableNotifications_false() {
		ChatDto chatDto = ChatDto.builder().chatId(653606407L).build();
		Long id = chatController.create(chatDto).getBody();

		telegramController.say("test_text");
		Chat chat = chatController.read(id).getBody();

		List<History> historyList = chat.getHistoryList();
		Assertions.assertEquals(0, historyList.size());
	}

//	@Test
//	void enableNotifications_true_test() {
//		ChatDto chatDto = ChatDto.builder().chatId(653606407L).build();
//		Long id = chatController.create(chatDto).getBody();
//		Chat before = ChatUtil.of(id, chatDto);
//
//		telegramBinder.bind(BinderContainer.builder()
//				.chat(before)
//				.message("TG +")
//				.build());
//
//		Chat after = chatController.read(id).getBody();
//
//		List<Chat> chats = chatRepository.findAll();
//		Assertions.assertEquals(1, chats.size());
//		Assertions.assertTrue(after.isEnableNotifications());
//	}

	@Test
	void telegram_binder_can_deactivate_notifications() {
		List<Chat> all = chatRepository.findAll();
		Assertions.assertEquals(0, all.size());

		ChatDto chatDto = ChatDto.builder().chatId(653606407L).enableNotifications(true).build();
		Long id = chatController.create(chatDto).getBody();
		Chat chat = ChatUtil.of(chatDto);
		chat.setId(id);

		telegramBinder.bind(BinderContainer.builder()
				.chat(chat)
				.message("TG")
				.build());

		Chat after = chatController.read(id).getBody();
		Assertions.assertFalse(after.isEnableNotifications());
	}
}