package com.company.app.telegram.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "CHAT")
public class Chat {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "CHAT_ID")
	private Long chatId;

	@Column(name = "ENABLE_NOTIFICATIONS")
	private boolean enableNotifications;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_INFO_ID", referencedColumnName = "id")
	UserInfo userInfo;

	@OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<History> historyList;

	@ManyToMany
	@JoinTable(name="CHATS_SUBSCRIPTIONS")
	private List<Subscription> subscriptions;
}
