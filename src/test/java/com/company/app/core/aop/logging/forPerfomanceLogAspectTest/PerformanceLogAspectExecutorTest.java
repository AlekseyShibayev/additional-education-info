package com.company.app.core.aop.logging.forPerfomanceLogAspectTest;

import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity.Context;
import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PerformanceLogAspectExecutorTest {

	@PerformanceLogAnnotation
	public void anyMethodNameWithEmptyAnnotation(Context context) {
		log.debug("1. anyMethodNameWithEmptyAnnotation");
	}

	@PerformanceLogAnnotation(isFirst = true)
	public void anyMethodNameWithGuidAsFirstParameter(String guid) {
		log.debug("2. anyMethodNameWithGuidAsFirstParameter");
	}

	@PerformanceLogAnnotation(number = "0", methodName = "getGuidMethod")
	public void anyMethodNameWithNumberAndMethodName(Context context) {
		log.debug("3. anyMethodNameWithNumberAndMethodName");
	}

	@PerformanceLogAnnotation(number = "0", fieldName = "guid")
	public void anyMethodNameWithNumberAndFieldName(Context context) {
		log.debug("4. anyMethodNameWithNumberAndFieldName");
	}
}
