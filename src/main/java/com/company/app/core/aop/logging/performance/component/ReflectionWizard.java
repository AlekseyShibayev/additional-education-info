package com.company.app.core.aop.logging.performance.component;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ReflectionWizard - делает Reflection магию.
 *
 * @author shibaev.aleksey 04.04.2023
 */
@Component
public class ReflectionWizard {

	@Autowired
	ReflectionUnderworld reflectionUnderworld;

	public Object getOriginalObjectFromSignature(ProceedingJoinPoint proceedingJoinPoint, String number) {
		return proceedingJoinPoint.getArgs()[Integer.parseInt(number)];
	}

	public <T extends Annotation> T getAnnotation(ProceedingJoinPoint proceedingJoinPoint, Class<T> type) {
		Method ownersMethod = getOwnersMethod(proceedingJoinPoint, type);
		return ownersMethod.getAnnotation(type);
	}

	public Method getOwnersMethod(ProceedingJoinPoint proceedingJoinPoint, Class<? extends Annotation> type) {
		Signature signature = proceedingJoinPoint.getSignature();
		List<Method> collect = Arrays.stream(signature.getDeclaringType().getDeclaredMethods())
				.filter(method -> method.getName().equals(signature.getName()))
				.filter(method -> method.isAnnotationPresent(type))
				.collect(Collectors.toList());

		if (collect.size() == 1) {
			return collect.get(0);
		} else {
			return reflectionUnderworld.getMethodIfOverloading(proceedingJoinPoint, collect);
		}
	}

	@SneakyThrows
	public <T> Method recursiveMethodSearch(Class<T> clazz, String methodName) {
		List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
				.filter(method -> method.getName().equals(methodName))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(methods) ? recursiveMethodSearch(clazz.getSuperclass(), methodName) : methods.get(0);
	}

	@SneakyThrows
	public <T> Field recursiveFieldSearch(Class<T> clazz, String fieldName) {
		List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
				.filter(field -> field.getName().equals(fieldName))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(fields) ? recursiveFieldSearch(clazz.getSuperclass(), fieldName) : fields.get(0);
	}
}
