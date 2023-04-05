package com.company.app.core.aop.logging;

import com.company.app.AbstractTest;
import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity.ExperimentContext;
import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity.ExperimentContextChild;
import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.PerformanceLogAspectExecutorTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Все методы,
 * кроме "1. anyMethodNameWithEmptyAnnotation"
 * должны вытащить GUID.
 */
@Slf4j
public class PerformanceLogAspectTest extends AbstractTest {

	@Autowired
	PerformanceLogAspectExecutorTest performanceLogAspectExecutor;

	private static final String GUID = "00000000-0000-0000-0000-111111111111";

	@Test
	public void ifPerformanceLogAnnotationAdviceWithEmptyAnnotationTest() {
		log.debug("__________");
		ExperimentContextChild experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithEmptyAnnotation(experimentContext);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithGuidAsParameterTest() {
		log.debug("__________");
		ExperimentContextChild experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithGuidAsParameter(experimentContext, GUID);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithNumberAndMethodNameTest() {
		log.debug("__________");
		ExperimentContext experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndMethodName(experimentContext);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithNumberAndFieldNameTest() {
		log.debug("__________");
		ExperimentContext experimentContext = new ExperimentContextChild();
		experimentContext.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndFieldName(experimentContext);
	}
}