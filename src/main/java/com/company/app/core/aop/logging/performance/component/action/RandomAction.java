package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.ActionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomAction extends AbstractAction {

	@Override
	public ActionType getType() {
		return ActionType.RANDOM;
	}

	@Override
	public String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		return UUID.randomUUID().toString();
	}
}
