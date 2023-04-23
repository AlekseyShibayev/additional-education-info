package com.company.app.exchangerate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ALIEXPRESS_EXCHANGE_RATE")
	private String aliexpressExchangeRate;

	@Column(name = "CREATION_DATE")
	private Date creationDate;
}
