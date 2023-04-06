package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.component.ActionType;
import com.company.app.core.aop.logging.performance.testEntity.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PerformanceLogOverloadingExperiment {

	@PerformanceLogAnnotation
	public void methodName() {}

	@PerformanceLogAnnotation
	public void methodName(String string) {}

	@PerformanceLogAnnotation(actionType = ActionType.NUMBER_AND_FIELD, number = "0", fieldName = "guid")
	public void methodName(Context context) {}

	@PerformanceLogAnnotation
	public void methodName(Context context, String string) {}
}
