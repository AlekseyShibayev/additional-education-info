package com.company.app.telegram.dto;

import com.company.app.telegram.entity.History;
import com.company.app.telegram.entity.Subscription;
import com.company.app.telegram.entity.UserInfo;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {

	private Long id;
	private Long chatId;
	private boolean enableNotifications;
	private List<History> historyList;
	private UserInfo userInfo;
	private Set<Subscription> subscriptions;
}
