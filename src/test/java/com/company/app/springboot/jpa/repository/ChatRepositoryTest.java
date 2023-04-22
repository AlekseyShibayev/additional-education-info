package com.company.app.springboot.jpa.repository;

import com.company.app.springboot.jpa.DataJpaSpringBootTestContext;
import com.company.app.telegram.repository.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;

@Slf4j
class ChatRepositoryTest extends DataJpaSpringBootTestContext {

	@Autowired
	ChatRepository chatRepository;

	@BeforeEach
	void init() {
//		chatRepository.deleteAll();
	}

	@Test
	void N_plus_one_test(CapturedOutput capture) {
		log.debug(SELECT_DELIMITER);
		chatRepository.findById(1L);
		String[] split = capture.getAll().split(SELECT_DELIMITER);
		String queryMessages = split[1];
		Assertions.assertEquals(1, StringUtils.countMatches(queryMessages, "select"));
	}
}
