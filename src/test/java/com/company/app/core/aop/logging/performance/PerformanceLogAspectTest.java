package com.company.app.core.aop.logging.performance;

import com.company.app.springboot.application.SpringBootApplicationContext;
import com.company.app.core.aop.logging.performance.testEntity.ExperimentContextChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.UUID;

/**
 * Все методы,
 * кроме "1. anyMethodNameWithEmptyAnnotation"
 * должны вытащить GUID.
 */
class PerformanceLogAspectTest extends SpringBootApplicationContext {

	@Autowired
	PerformanceLogAspectExecutorTest performanceLogAspectExecutor;

	private static final String GUID = "11111111-1111-1111-1111-111111111111";

	@Test
	@ExtendWith(OutputCaptureExtension.class)
	void withEmptyAnnotationTest(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithEmptyAnnotation(child);
		Assertions.assertNotNull(capture.getOut());
	}

	@Test
	@ExtendWith(OutputCaptureExtension.class)
	void testWithGuidAsParameter(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithGuidAsParameter(child, GUID);
		Assertions.assertTrue(capture.getOut().contains(GUID));
	}

	@Test
	@ExtendWith(OutputCaptureExtension.class)
	void testWithNumberAndMethodName(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndMethodName(child);
		Assertions.assertTrue(capture.getOut().contains(GUID));
	}

	@Test
	@ExtendWith(OutputCaptureExtension.class)
	void testWithNumberAndFieldName(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		performanceLogAspectExecutor.anyMethodNameWithNumberAndFieldName(child);
		Assertions.assertTrue(capture.getOut().contains(GUID));
	}
}