package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.action.Action;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author shibaev.aleksey 04.04.2023
 */
@Slf4j
@Component
public class GuidExtractor {

	@Autowired
	ReflectionWizard reflectionWizard;
	@Autowired
	ActionRegistry actionRegistry;

	public String extractGuid(ProceedingJoinPoint proceedingJoinPoint) {
		String result = StringUtils.EMPTY;
		Stopwatch stopwatch = Stopwatch.createStarted();

		try {
			PerformanceLogAnnotation annotation = reflectionWizard.getAnnotation(proceedingJoinPoint.getSignature(), PerformanceLogAnnotation.class);
			Action action = actionRegistry.getActions().get(annotation.actionType());
			result = action.getGuid(proceedingJoinPoint, annotation);
		} catch (Exception e) {
			log.trace(e.getMessage(), e);
			result = UUID.randomUUID().toString();
		} finally {
			stopwatch.stop();
			log.debug("[{}] выковыривание guid заняло [{}] ms.", result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
		}
		return result;
	}
}
