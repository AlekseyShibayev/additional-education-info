package com.company.app.telegram.controller;

import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.service.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telegram/chat")
public class ChatController {

	@Autowired
	ChatService chatService;

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Long> create(@RequestBody ChatDto chatDto) {
		return ResponseEntity.ok(chatService.create(chatDto));
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Chat> read(@PathVariable Long id) {
		return ResponseEntity.ok(chatService.read(id));
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Boolean> update(@PathVariable Long id,
										  @RequestBody ChatDto chatDto) {
		return ResponseEntity.ok(chatService.update(id, chatDto));
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return ResponseEntity.ok(chatService.delete(id));
	}
}
