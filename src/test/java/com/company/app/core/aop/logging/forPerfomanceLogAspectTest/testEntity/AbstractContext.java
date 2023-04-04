package com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity;

import lombok.Setter;

import java.util.UUID;

public abstract class AbstractContext implements Context {

	@Setter
	UUID guid;
}
