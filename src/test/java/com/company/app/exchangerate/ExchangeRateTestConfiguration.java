package com.company.app.exchangerate;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.core.tool.impl.DataExtractorToolImpl;
import com.company.app.exchangerate.component.ExchangeRateFacade;
import com.company.app.exchangerate.component.api.ExchangeRateExtractor;
import com.company.app.exchangerate.component.impl.ExchangeRateExtractorImpl;
import com.company.app.exchangerate.repository.ExchangeRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@MockBean(ExchangeRepository.class)
public class ExchangeRateTestConfiguration {

	@Bean
	ExchangeRateFacade exchangeRateFacade(ExchangeRateExtractor exchangeRateExtractor){
		return new ExchangeRateFacade();
	}

	@Bean
	ExchangeRateExtractor exchangeRateExtractor(DataExtractorTool dataExtractorTool, ExchangeRepository exchangeRepository){
		return new ExchangeRateExtractorImpl();
	}

	@Bean
	DataExtractorTool dataExtractorTool(){
		return new DataExtractorToolImpl();
	}
}