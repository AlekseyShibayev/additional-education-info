package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import lombok.SneakyThrows;
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
 * ReflectionWizard - делает Reflection магию.
 *
 * @author shibaev.aleksey 04.04.2023
 */
@Component
public class ReflectionWizard {

	public  <T extends Annotation> T getAnnotation(Signature signature, Class<T> type) {
		Method ownersMethod = getOwnersMethod(signature);
		return ownersMethod.getAnnotation(type);
	}

	public Method getOwnersMethod(Signature signature) {
		for (Method method : signature.getDeclaringType().getDeclaredMethods()) {
			if ((method.getName().equals(signature.getName()) && method.isAnnotationPresent(PerformanceLogAnnotation.class))) {
				return method;
			}
		}
		throw new RuntimeException("этого никогда не случится, т.к. нас кто-то да вызвал.");
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
