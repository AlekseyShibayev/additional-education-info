package com.company.app.core.aop.logging.forPerfomanceLogAspectTest;

import com.company.app.core.aop.logging.forPerfomanceLogAspectTest.testEntity.Context;
import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.ActionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 4 примера использования @PerformanceLogAnnotation
 */
@Slf4j
@Component
public class PerformanceLogAspectExecutorTest {

	@PerformanceLogAnnotation()
	public void anyMethodNameWithEmptyAnnotation(Context context) {
		log.debug("1. anyMethodNameWithEmptyAnnotation");
	}

	@PerformanceLogAnnotation(actionType = ActionType.NUMBER, number = "1")
	public void anyMethodNameWithGuidAsParameter(Context context, String guid) {
		log.debug("2. anyMethodNameWithGuidAsParameter");
	}

	@PerformanceLogAnnotation(actionType = ActionType.NUMBER_AND_METHOD, number = "0", methodName = "getGuidMethod")
	public void anyMethodNameWithNumberAndMethodName(Context context) {
		log.debug("3. anyMethodNameWithNumberAndMethodName");
	}

	@PerformanceLogAnnotation(actionType = ActionType.NUMBER_AND_FIELD, number = "0", fieldName = "guid")
	public void anyMethodNameWithNumberAndFieldName(Context context) {
		log.debug("4. anyMethodNameWithNumberAndFieldName");
	}
}
