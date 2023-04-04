package com.company.app.core.aop.logging;

import com.company.app.AbstractTest;
import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity.ExperimentContext;
import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity.ExperimentContextChild;
import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.PerformanceLogAspectExecutorTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Все методы,
 * кроме "1. anyMethodNameWithEmptyAnnotation"
 * должны вытащить GUID.
 */
public class PerformanceLogAspectTest extends AbstractTest {

	@Autowired
	PerformanceLogAspectExecutorTest performanceLogAspectExecutor;

	private static final String GUID = "00000000-0000-0000-0000-111111111111";

	@Test
	public void ifPerformanceLogAnnotationAdviceWithEmptyAnnotationTest() {
		ExperimentContextChild experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithEmptyAnnotation(experimentContext);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithGuidAsFirstParameterTest() {
		performanceLogAspectExecutor.anyMethodNameWithGuidAsFirstParameter(GUID);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithNumberAndMethodNameTest() {
		ExperimentContext experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndMethodName(experimentContext);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithNumberAndFieldNameTest() {
		ExperimentContext experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndFieldName(experimentContext);
	}
}