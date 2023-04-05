package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.ActionType;
import org.aspectj.lang.ProceedingJoinPoint;

public interface Action {

	ActionType getActionType();

	String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation);
}
