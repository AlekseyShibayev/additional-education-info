package com.company.app.telegram.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "HISTORY")
public class History {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "DATE")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "CHAT_ID", nullable = false)
	private Chat chat;
}