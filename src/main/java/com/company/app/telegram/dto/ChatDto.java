package com.company.app.telegram.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {

	private Long chatId;
	private boolean enableNotifications;
	private String role;
}
