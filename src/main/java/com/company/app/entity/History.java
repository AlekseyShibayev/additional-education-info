package com.company.app.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "message")
	private String message;
}