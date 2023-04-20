package com.company.app.telegram.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CHAT")
public class Chat {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "CHAT_ID")
	private Long chatId;

	@Column(name = "ROLE")
	private String role;

	@OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
	private List<History> historyList;
}
