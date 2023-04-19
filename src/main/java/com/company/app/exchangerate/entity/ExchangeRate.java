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
@Table(name = "exchange_rate")
public class ExchangeRate {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "aliexpress_exchange_rate")
	private String aliexpressExchangeRate;

	@Column(name = "date")
	private Date date;
}
