package com.company.app.exchangerate;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.core.tool.impl.DataExtractorToolImpl;
import com.company.app.exchangerate.repository.ExchangeRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ExchangeRateTestConfiguration {

	@MockBean
	ExchangeRepository exchangeRepository;

	@Bean
	DataExtractorTool dataExtractorTool() {
		return new DataExtractorToolImpl();
	}
}