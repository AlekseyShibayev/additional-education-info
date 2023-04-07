package com.company.app.core.aop.logging.performance.component.impl;

import com.company.app.core.aop.logging.performance.component.api.Reflector;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shibaev.aleksey 04.04.2023
 */
@Component
public class ReflectorImpl implements Reflector {

	/**
	 * Случаи с перегрузками методов рассматривать не будем.
	 * Ошибки тут не будет, как минимум 1 метод нас дёрнул по аннотации.
	 */
	public Method getMethodThatCalledUs(ProceedingJoinPoint proceedingJoinPoint, Class<? extends Annotation> type) {
		Signature signature = proceedingJoinPoint.getSignature();
		return Arrays.stream(signature.getDeclaringType().getDeclaredMethods())
				.filter(method -> method.getName().equals(signature.getName()))
				.filter(method -> method.isAnnotationPresent(type))
				.filter(method -> sameNumberOfArgs(proceedingJoinPoint, method))
				.findFirst()
				.orElseThrow();
	}

	public Object getArg(ProceedingJoinPoint proceedingJoinPoint, String number) {
		return proceedingJoinPoint.getArgs()[Integer.parseInt(number)];
	}

	public <T extends Annotation> T getAnnotation(ProceedingJoinPoint proceedingJoinPoint, Class<T> type) {
		Method ownersMethod = getMethodThatCalledUs(proceedingJoinPoint, type);
		return ownersMethod.getAnnotation(type);
	}

	private boolean sameNumberOfArgs(ProceedingJoinPoint proceedingJoinPoint, Method method) {
		return method.getParameters().length == proceedingJoinPoint.getArgs().length;
	}

	@SneakyThrows
	public Method recursiveMethodSearch(Class<?> clazz, String methodName) {
		List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
				.filter(method -> method.getName().equals(methodName))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(methods) ? recursiveMethodSearch(clazz.getSuperclass(), methodName) : methods.get(0);
	}

	@SneakyThrows
	public Field recursiveFieldSearch(Class<?> clazz, String fieldName) {
		List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
				.filter(field -> field.getName().equals(fieldName))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(fields) ? recursiveFieldSearch(clazz.getSuperclass(), fieldName) : fields.get(0);
	}
}
