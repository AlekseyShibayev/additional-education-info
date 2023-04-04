package com.company.app.core.aop.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, для работы PerformanceLogAspect
 * number - порядковый номер объекта в сигнатуре метода, начинается с 0.
 * methodName - метод объекта, возвращающего UUID или String в формате UUID. Метод должен быть без аргументов.
 *
 * @author shibaev.aleksey 30.03.2023
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogAnnotation {

	String number() default "";

	String methodName() default "";
}
