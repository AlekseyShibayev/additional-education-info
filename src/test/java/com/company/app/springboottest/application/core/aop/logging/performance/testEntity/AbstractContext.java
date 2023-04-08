package com.company.app.springboottest.application.core.aop.logging.performance.testEntity;

import lombok.Setter;

import java.util.UUID;

public abstract class AbstractContext implements Context {

	@Setter
	UUID guid;
}
