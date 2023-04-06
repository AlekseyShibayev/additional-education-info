package com.company.app.core.aop.logging.performance;

import com.company.app.AbstractTest;
import com.company.app.core.aop.logging.performance.testEntity.ExperimentContextChild;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
public class PerformanceLogOverloadingExperimentTest extends AbstractTest {

	@Autowired
	PerformanceLogOverloadingExperiment performanceLogOverloadingExperiment;

	@Test
	public void overloadingTest() {
		log.debug("__________");
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(PerformanceLogAspectTest.GUID));
		performanceLogOverloadingExperiment.methodName(child);
	}
}
