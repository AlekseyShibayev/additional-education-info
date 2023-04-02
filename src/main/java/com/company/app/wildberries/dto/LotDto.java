package com.company.app.wildberries.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LotDto {

	private String name;
	private String price;
	private String discount;
}
