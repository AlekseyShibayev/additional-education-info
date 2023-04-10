package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.ActionType;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.UUID;

@Component
public class NumberAndFieldAction extends AbstractAction {

	@Override
	public ActionType getType() {
		return ActionType.NUMBER_AND_FIELD;
	}

	@SneakyThrows
	@Override
	public String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		String fieldName = annotation.fieldName();
		Object originalObjectFromSignature = reflector.getArg(proceedingJoinPoint, annotation.number());

		Field field = reflector.recursiveFieldSearch(originalObjectFromSignature.getClass(), fieldName);
		field.trySetAccessible();
		Object value = field.get(originalObjectFromSignature);

		UUID uuid = UUID.fromString(String.valueOf(value));
		return uuid.toString();
	}
}
