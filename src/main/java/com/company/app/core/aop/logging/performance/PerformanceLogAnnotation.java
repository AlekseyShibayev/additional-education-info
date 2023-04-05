package com.company.app.core.aop.logging.performance;

import java.lang.annotation.*;

/**
 * Аннотация, для работы PerformanceLogAspect.
 * <p>
 * Параметры для определения GUID:
 * 1) number - порядковый номер объекта в сигнатуре метода, начинается с 0.
 * Если задать только его, то попытается вытащить GUID из i'того объекта в сигнатуре.
 * 2) methodName - метод объекта, взятого по number из сигнатуры, возвращающего UUID или String в формате UUID.
 * Метод должен быть без аргументов.
 * 3) fieldName - поле объекта, взятого по number из сигнатуры, содержащее UUID или String в формате UUID.
 * <p>
 * Если задать и fieldName и methodName, то будет использоваться fieldName. Но лучше так не делать.
 * <p>
 * Примеры см. в тестах PerformanceLogAspectExecutorTest и PerformanceLogAspectTest
 *
 * @author shibaev.aleksey 30.03.2023
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogAnnotation {

	String number() default "";

	String methodName() default "";

	String fieldName() default "";
}
