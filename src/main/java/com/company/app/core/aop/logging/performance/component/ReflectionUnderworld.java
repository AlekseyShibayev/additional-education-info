package com.company.app.core.aop.logging.performance.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
public class ReflectionUnderworld {

	public Method getMethodIfOverloading(ProceedingJoinPoint proceedingJoinPoint, List<Method> collect) {
		throw new RuntimeException("Такое возможно, если оверлоадинг.");
	}
}
