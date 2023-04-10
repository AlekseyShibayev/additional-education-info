package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.ActionType;
import org.aspectj.lang.ProceedingJoinPoint;

public interface Action {

	ActionType getType();

	String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation);

	/**
	 * Это конечно красивое решение, но:
	 * 1. Оно нарушает S (Single Responsibility Principle) - Action кроме добывания GUID теперь ещё ответственный за свою регистрацию.
	 * 2. Теряется смысл IoC, ответственность за создание и настройку бинов, которые мы отдали Spring'у.
	 * 3. Если использовать lazy в тестах, то Action будет создан, но его никто не дёрнет, он не зарегистрируется в регистратуре и мапа<Action> будет пуста.
 	 */
//	@Autowired
//	default void registerMyself(ActionRegistry actionRegistry) {
//		actionRegistry.registerAction(this);
//	}
}
