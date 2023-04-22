package com.company.app.telegram.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "CHAT")
@NamedEntityGraph(
		name = "Chat.all",
		attributeNodes = {
				@NamedAttributeNode("subscriptions"),
				@NamedAttributeNode("historyList"),
				@NamedAttributeNode("userInfo")
		}
)
public class Chat {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "CHAT_ID")
	private Long chatId;

	@Column(name = "ENABLE_NOTIFICATIONS")
	private boolean enableNotifications;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_INFO_ID", referencedColumnName = "id")
	private UserInfo userInfo;

	@OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<History> historyList;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CHATS_SUBSCRIPTIONS")
	private Set<Subscription> subscriptions;
}
