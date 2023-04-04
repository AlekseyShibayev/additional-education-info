package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
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

		Signature signature = proceedingJoinPoint.getSignature();
		try {
			Method ownersMethod = reflectionWizard.getOwnersMethod(signature);
			PerformanceLogAnnotation annotation = ownersMethod.getAnnotation(PerformanceLogAnnotation.class);
			if (StringUtils.isNotEmpty(annotation.number())) {
				result = getGuidByAnnotationParameters(proceedingJoinPoint, annotation);
			} else {
				result = getGuidByFirstSignatureParameter(proceedingJoinPoint);
			}
		} catch (Exception e) {
			log.trace(e.getMessage(), e);
			result = UUID.randomUUID().toString();
		} finally {
			stopwatch.stop();
			log.debug("[{}] выковыривание guid заняло [{}] ms.", result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
		}
		return result;
	}

	private String getGuidByFirstSignatureParameter(ProceedingJoinPoint proceedingJoinPoint) {
		Object[] args = proceedingJoinPoint.getArgs();
		UUID uuid = UUID.fromString(String.valueOf(args[0]));
		return uuid.toString();
	}

	private String getGuidByAnnotationParameters(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		String methodName = annotation.methodName();
		String fieldName = annotation.fieldName();
		if (isInvalidCase(methodName, fieldName)) {
			throw new RuntimeException("Взаимоисключающие параметры.");
		}
		int i = Integer.parseInt(annotation.number());
		Object originalObjectFromSignature = proceedingJoinPoint.getArgs()[i];
		Object value = getValueFromOriginalObject(methodName, fieldName, originalObjectFromSignature);
		UUID uuid = UUID.fromString(String.valueOf(value));
		return uuid.toString();
	}

	private boolean isInvalidCase(String methodName, String fieldName) {
		if (StringUtils.isNotEmpty(methodName) && StringUtils.isNotEmpty(fieldName)) {
			return true;
		} else if (StringUtils.isEmpty(methodName) && StringUtils.isEmpty(fieldName)) {
			return true;
		} else {
			return false;
		}
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
