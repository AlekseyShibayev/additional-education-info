package com.company.app.application;

import com.company.app.AbstractSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

class ApplicationTest extends AbstractSpringBootTest {

	@Autowired ApplicationContext applicationContext;

	@Test
	void context_should_start() {
		Assertions.assertNotNull(applicationContext);
	}
}
