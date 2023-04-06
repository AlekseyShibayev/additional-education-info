package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.component.ActionType;

import java.lang.annotation.*;

/**
 * Аннотация, для работы {@link PerformanceLogAspect}
 * <p>
 * Параметры для определения GUID:
 * <p>
 * {@code actionType} - это enum {@link ActionType}, в котором перечислены способы вытаскивания GUID
 * <p>
 * {@code number} - порядковый номер объекта в сигнатуре метода, начинается с 0.
 * <p>
 * {@code methodName} - метод объекта, взятого по number из сигнатуры, возвращающего UUID или String в формате UUID.
 * Метод должен быть без аргументов.
 * <p>
 * {@code fieldName} - поле объекта, взятого по number из сигнатуры, содержащее UUID или String в формате UUID.
 * <p>
 * Примеры использования: {@link com.company.app.core.aop.logging.performance.PerformanceLogAspectExecutorTest}
 *
 * @author shibaev.aleksey 30.03.2023
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogAnnotation {

	ActionType actionType() default ActionType.RANDOM;

	String number() default "";

	String methodName() default "";

	String fieldName() default "";
}
