package com.company.app.experiment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotForExperiment {

	private Long id;
	private String name;
	private String price;
	private String discount;
	private List<String> someList;
}
