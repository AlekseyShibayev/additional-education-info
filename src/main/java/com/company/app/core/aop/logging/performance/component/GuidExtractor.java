package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

	public String extractGuid(ProceedingJoinPoint proceedingJoinPoint) {
		String result = StringUtils.EMPTY;
		Stopwatch stopwatch = Stopwatch.createStarted();

		try {
			PerformanceLogAnnotation annotation = reflectionWizard.getAnnotation(proceedingJoinPoint.getSignature(), PerformanceLogAnnotation.class);
			result = getGuid(proceedingJoinPoint, annotation);
		} catch (Exception e) {
			log.trace(e.getMessage(), e);
			result = UUID.randomUUID().toString();
		} finally {
			stopwatch.stop();
			log.debug("[{}] выковыривание guid заняло [{}] ms.", result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
		}
		return result;
	}

	private String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		if (StringUtils.isNotEmpty(annotation.number())) {
			return getGuidByAnnotationParameters(proceedingJoinPoint, annotation);
		} else {
			return UUID.randomUUID().toString();
		}
	}

	private String getGuidByAnnotationParameters(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		String number = annotation.number();
		String methodName = annotation.methodName();
		String fieldName = annotation.fieldName();

		if (isNumberOnly(methodName, fieldName)) {
			return getGuidBySignatureParameter(proceedingJoinPoint, number);
		} else {
			return getGuidBySignatureParameter(proceedingJoinPoint, number, methodName, fieldName);
		}
	}

	private boolean isNumberOnly(String methodName, String fieldName) {
		return StringUtils.isEmpty(methodName) && StringUtils.isEmpty(fieldName);
	}

	private String getGuidBySignatureParameter(ProceedingJoinPoint proceedingJoinPoint, String number) {
		Object originalObjectFromSignature = getOriginalObjectFromSignature(proceedingJoinPoint, number);
		UUID uuid = UUID.fromString(String.valueOf(originalObjectFromSignature));
		return uuid.toString();
	}

	private Object getOriginalObjectFromSignature(ProceedingJoinPoint proceedingJoinPoint, String number) {
		return proceedingJoinPoint.getArgs()[Integer.parseInt(number)];
	}

	private String getGuidBySignatureParameter(ProceedingJoinPoint proceedingJoinPoint, String number, String methodName, String fieldName) {
		Object originalObjectFromSignature = getOriginalObjectFromSignature(proceedingJoinPoint, number);
		Object value = getValueFromOriginalObject(methodName, fieldName, originalObjectFromSignature);
		UUID uuid = UUID.fromString(String.valueOf(value));
		return uuid.toString();
	}

	@SneakyThrows
	private Object getValueFromOriginalObject(String methodName, String fieldName, Object originalObjectFromSignature) {
		if (StringUtils.isNotEmpty(fieldName)) {
			Field field = reflectionWizard.recursiveFieldSearch(originalObjectFromSignature.getClass(), fieldName);
			field.trySetAccessible();
			return field.get(originalObjectFromSignature);
		} else {
			Method method = reflectionWizard.recursiveMethodSearch(originalObjectFromSignature.getClass(), methodName);
			method.trySetAccessible();
			return method.invoke(originalObjectFromSignature);
		}
	}
}
