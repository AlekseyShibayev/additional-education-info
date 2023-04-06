package com.company.app.core.aop.logging.performance.component.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface GuidExtractor {

	String extractGuid(ProceedingJoinPoint proceedingJoinPoint);
}
