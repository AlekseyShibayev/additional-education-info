package com.company.app.wildberries.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "LOT")
public class Lot {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ARTICLE")
	private String article;

	@Column(name = "DESIRED_PRICE")
	private String desiredPrice;

	@Column(name = "DISCOUNT")
	private String discount;
}
