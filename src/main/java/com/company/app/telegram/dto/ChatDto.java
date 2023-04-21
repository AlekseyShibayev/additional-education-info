package com.company.app.telegram.dto;

import com.company.app.telegram.entity.Subscription;
import com.company.app.telegram.entity.UserInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {

	private Long chatId;
	private boolean enableNotifications;
	UserInfo userInfo;
	List<Subscription> subscriptions;
}
