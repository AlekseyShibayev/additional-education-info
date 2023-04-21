package com.company.app.telegram.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class UserInfo {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@OneToOne(mappedBy = "userInfo")
	Chat chat;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ROLE")
	private String role;


}
