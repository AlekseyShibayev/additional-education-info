package com.company.app.core.aop.logging.performance.component.api;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface Reflector {

	Method getMethodThatCalledUs(ProceedingJoinPoint proceedingJoinPoint, Class<? extends Annotation> type);

	Object getArg(ProceedingJoinPoint proceedingJoinPoint, String number);

	<T extends Annotation> T getAnnotation(ProceedingJoinPoint proceedingJoinPoint, Class<T> type);

	Method recursiveMethodSearch(Class<?> clazz, String methodName);

	Field recursiveFieldSearch(Class<?> clazz, String fieldName);
}
