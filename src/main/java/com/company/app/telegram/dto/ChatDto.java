package com.company.app.telegram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatDto {

	private Long chatId;
	private boolean enableNotifications;
	private String role;
}
