package com.company.app.springboot.web;

import com.company.app.wildberries.service.api.LotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

@Slf4j
@TestPropertySource("/test.properties")
@WebMvcTest
public abstract class WebMvcSpringBootTestContext {

	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected ObjectMapper objectMapper;

	@MockBean
	protected LotService lotService;

	@PostConstruct
	void init() {
		log.debug("**********     запущена группа тестов веб морды     **********");
	}
}
