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
	public ActionType getActionType() {
		return ActionType.NUMBER_AND_FIELD;
	}

	@SneakyThrows
	@Override
	public String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		String fieldName = annotation.fieldName();
		Object originalObjectFromSignature = reflectionWizard.getOriginalObjectFromSignature(proceedingJoinPoint, annotation.number());

		Field field = reflectionWizard.recursiveFieldSearch(originalObjectFromSignature.getClass(), fieldName);
		field.trySetAccessible();
		Object value = field.get(originalObjectFromSignature);

		UUID uuid = UUID.fromString(String.valueOf(value));
		return uuid.toString();
	}
}
