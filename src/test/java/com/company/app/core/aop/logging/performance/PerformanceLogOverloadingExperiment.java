package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.component.ActionType;
import com.company.app.core.aop.logging.performance.testEntity.Context;
import com.company.app.core.aop.logging.performance.testEntity.ExperimentContextChild;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;

@Slf4j
public class PerformanceLogOverloadingExperiment {

	@Test
	public void overloadingTest() {
		log.debug("__________");
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(PerformanceLogAspectTest.GUID));
		methodName(child);
	}

	@PerformanceLogAnnotation
	public void methodName() {}

	@PerformanceLogAnnotation
	public void methodName(String string) {}

	@PerformanceLogAnnotation(actionType = ActionType.NUMBER_AND_FIELD, number = "0", fieldName = "guid")
	public void methodName(Context context) {}

	@PerformanceLogAnnotation
	public void methodName(Context context, String string) {}
}
