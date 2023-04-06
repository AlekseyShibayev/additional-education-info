package com.company.app.core.aop.logging.performance.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReflectionUnderworld {

	public Method getMethodIfOverloading(ProceedingJoinPoint proceedingJoinPoint, List<Method> collect) {
		return collect.stream()
				.filter(method -> isEquals(proceedingJoinPoint.getArgs(), method.getParameters()))
				.findFirst()
				.get();
	}

	private boolean isEquals(Object[] args, Parameter[] parameters) {
		boolean result = true;
		for (int i = 0; i < parameters.length; i++) {
			Object realObject = args[i];
			Parameter parameter = parameters[i];
			Class<?> type = parameter.getType();

			if (!isEqualsClass(realObject.getClass(), type)) {
				return false;
			}
		}
		return result;
	}

	private boolean isEqualsClass(Class<?> realObjectClass, Class<?> type) {
		if (realObjectClass.equals(type)) {
			return true;
		} else {
			if (!isNextParentObject(realObjectClass)) {
				return isEqualsClass(realObjectClass.getSuperclass(), type);

			} else {
				Class<?>[] interfaces = realObjectClass.getInterfaces();
				if (interfaces.length == 0) {
					return false;
				} else {
					List<Class<?>> collect = Arrays.stream(interfaces)
							.collect(Collectors.toList());
					return true;
				}
			}
		}
	}

	private boolean isNextParentObject(Class<?> clazz) {
		return clazz.getSuperclass().equals(Object.class);
	}
}
