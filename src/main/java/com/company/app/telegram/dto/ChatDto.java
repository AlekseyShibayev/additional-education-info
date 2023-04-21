package com.company.app.telegram.dto;

import com.company.app.telegram.entity.UserInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {

	private Long chatId;
	private boolean enableNotifications;
	UserInfo userInfo;
}
