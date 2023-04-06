package com.company.app.core.aop.logging.performance;

import com.company.app.AbstractTest;
import com.company.app.core.aop.logging.performance.testEntity.ExperimentContextChild;
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

	static final String GUID = "11111111-1111-1111-1111-111111111111";

	@Test
	public void ifPerformanceLogAnnotationAdviceWithEmptyAnnotationTest() {
		log.debug("__________");
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithEmptyAnnotation(child);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithGuidAsParameterTest() {
		log.debug("__________");
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithGuidAsParameter(child, GUID);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithNumberAndMethodNameTest() {
		log.debug("__________");
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndMethodName(child);
	}

	@Test
	public void ifPerformanceLogAnnotationAdviceWithNumberAndFieldNameTest() {
		log.debug("__________");
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndFieldName(child);
	}
}