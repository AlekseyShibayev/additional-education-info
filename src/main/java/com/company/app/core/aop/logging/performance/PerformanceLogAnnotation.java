package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.component.ActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, для работы PerformanceLogAspect.
 * <p>
 * Параметры для определения GUID:
 * 1) actionType - это enum, в котором перечислены способы вытаскивания GUID
 * 2) number - порядковый номер объекта в сигнатуре метода, начинается с 0.
 * 3) methodName - метод объекта, взятого по number из сигнатуры, возвращающего UUID или String в формате UUID.
 * Метод должен быть без аргументов.
 * 4) fieldName - поле объекта, взятого по number из сигнатуры, содержащее UUID или String в формате UUID.
 * <p>
 * Примеры см. в тестах PerformanceLogAspectExecutorTest и PerformanceLogAspectTest
 *
 * @author shibaev.aleksey 30.03.2023
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogAnnotation {

	ActionType actionType() default ActionType.RANDOM;

	String number() default "";

	String methodName() default "";

	String fieldName() default "";
}
