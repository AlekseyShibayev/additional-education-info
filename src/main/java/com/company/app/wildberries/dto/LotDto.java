package com.company.app.wildberries.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class LotDto {

	private String article;
	private String desiredPrice;
	private String discount;
}
