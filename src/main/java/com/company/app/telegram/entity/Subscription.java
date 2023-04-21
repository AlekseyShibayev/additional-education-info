package com.company.app.telegram.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TYPE")
	private String type;

	@ManyToMany(mappedBy="subscriptions")
	private Set<Chat> chats;
}
