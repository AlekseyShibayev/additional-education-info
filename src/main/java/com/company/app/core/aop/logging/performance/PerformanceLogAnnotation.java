package com.company.app.core.aop.logging.performance;

import java.lang.annotation.*;

/**
 * Аннотация, для работы PerformanceLogAspect.
 * <p>
 * Параметры для определения GUID:
 * 1) isFirst - попытаться вытащить GUID из первого объекта в сигнатуре.
 * 2) number - порядковый номер объекта в сигнатуре метода, начинается с 0.
 * 3) methodName - метод объекта, возвращающего UUID или String в формате UUID. Метод должен быть без аргументов.
 * 4) fieldName - поле объекта, содержащее UUID или String в формате UUID.
 * <p>
 * Примеры см. в тестах PerformanceLogAspectExecutorTest и PerformanceLogAspectTest
 *
 * @author shibaev.aleksey 30.03.2023
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogAnnotation {

	boolean isFirst() default false;

	String number() default "";

	String methodName() default "";

	String fieldName() default "";
}
