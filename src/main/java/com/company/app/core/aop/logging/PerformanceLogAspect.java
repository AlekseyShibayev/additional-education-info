package com.company.app.core.aop.logging;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Аспект, логирует разными способами производительность метода.
 * Метод должен быть помечен аннотацией @PerformanceLogAnnotation
 * <p>
 * Способы получения GUID:
 * 1. В аннотацию можно передать параметры. Пример: @PerformanceLogAnnotation(number = "0", methodName = "guid")
 * number - порядковый номер объекта в сигнатуре метода, начинается с 0.
 * methodName - метод объекта, возвращающего UUID или String в формате UUID. Этот метод должен быть без аргументов.
 * 2. Аннотация без параметров. Пример: @PerformanceLogAnnotation
 * Тогда гуид попытается вытащить из первого объекта в сигнатуре.
 * 3. Если в п.1 или п.2 упали - сгенерируется рандомный гуид.
 * <p>
 * Способы логирования:
 * 1. Стандартный, пример:
 * PerformanceLogAspect] [] [] [76069a35-dab7-45bf-968e-a24281ac5a21]: запущен com.company.app.exchangeRate.ExchangeRateFacade.extract
 * PerformanceLogAspect] [] [] [76069a35-dab7-45bf-968e-a24281ac5a21]: за [1757] ms выполнен com.company.app.exchangeRate.ExchangeRateFacade.extract
 * <p>
 * 2. Для Collection - пишет размер, пример:
 * PerformanceLogAspect] [] [] [42db76b0-f2e2-4994-9b12-6fa603e62e72]: запущен com.company.app.wildberries.WildberriesFacade.getDesiredLots
 * PerformanceLogAspect] [] [] [42db76b0-f2e2-4994-9b12-6fa603e62e72]: за [351] ms вернул [0] шт. выполнен com.company.app.wildberries.WildberriesFacade.getDesiredLots
 *
 * @author shibaev.aleksey 30.03.2023
 */
@Slf4j
@Aspect
@Component
public class PerformanceLogAspect {

	@Pointcut("@annotation(PerformanceLogAnnotation)")
	public void ifPerformanceLogAnnotation() {
	}

	@SneakyThrows
	@Around("ifPerformanceLogAnnotation()")
	public Object ifPerformanceLogAnnotationAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		if (log.isDebugEnabled()) {
			Stopwatch stopwatch = Stopwatch.createStarted();
			Signature signature = proceedingJoinPoint.getSignature();
			String guid = getGuid(proceedingJoinPoint);
			doLogBefore(guid, signature);

			Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

			stopwatch.stop();
			doLogAfter(stopwatch, guid, signature, proceed);
			return proceed;
		} else {
			return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		}
	}

	private String getGuid(ProceedingJoinPoint proceedingJoinPoint) {
		Stopwatch stopwatch = Stopwatch.createStarted();

		Signature signature = proceedingJoinPoint.getSignature();
		try {
			Method method = getMethod(signature);
			PerformanceLogAnnotation annotation = method.getAnnotation(PerformanceLogAnnotation.class);
			if (StringUtils.isNotEmpty(annotation.number())) {
				return getGuidByMethod(proceedingJoinPoint, annotation);
			} else {
				return getGuidByFirstSignatureParameter(proceedingJoinPoint);
			}
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		} finally {
			stopwatch.stop();
			log.trace("выковыривание guid заняло [{}] ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
		}
	}

	private static String getGuidByFirstSignatureParameter(ProceedingJoinPoint proceedingJoinPoint) {
		Object[] args = proceedingJoinPoint.getArgs();
		UUID uuid = UUID.fromString(String.valueOf(args[0]));
		return uuid.toString();
	}

	@SneakyThrows
	private String getGuidByMethod(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
		int i = Integer.parseInt(annotation.number());
		Object originalObjectFromSignature = proceedingJoinPoint.getArgs()[i];
		Method method = recursiveMethodSearch(originalObjectFromSignature.getClass(), annotation.methodName());
		method.trySetAccessible();
		Object invoke = method.invoke(originalObjectFromSignature);
		UUID uuid = UUID.fromString(String.valueOf(invoke));
		return uuid.toString();
	}

	@SneakyThrows
	private <T> Method recursiveMethodSearch(Class<T> clazz, String methodName) {
		List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
				.filter(method -> method.getName().equals(methodName))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(methods) ? recursiveMethodSearch(clazz.getSuperclass(), methodName) : methods.get(0);
	}

	private Method getMethod(Signature signature) {
		for (Method method : signature.getDeclaringType().getDeclaredMethods()) {
			if ((method.getName().equals(signature.getName()) && method.isAnnotationPresent(PerformanceLogAnnotation.class))) {
				return method;
			}
		}
		throw new RuntimeException("этого никогда не случится, т.к. нас кто-то же вызвал.");
	}

	private void doLogAfter(Stopwatch stopwatch, String guid, Signature signature, Object proceed) {
		if (proceed instanceof Collection) {
			doCollectionPerformanceLogging(stopwatch, guid, signature, (Collection<?>) proceed);
		} else {
			doDefaultPerformanceLogging(stopwatch, guid, signature);
		}
	}

	private void doLogBefore(String guid, Signature signature) {
		log.debug("[{}]: запущен {}.{}",
				guid,
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doCollectionPerformanceLogging(Stopwatch stopwatch, String guid, Signature signature, Collection<?> proceed) {
		log.debug("[{}]: за [{}] ms вернул [{}] шт. выполнен {}.{}",
				guid,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				proceed.size(),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doDefaultPerformanceLogging(Stopwatch stopwatch, String guid, Signature signature) {
		log.debug("[{}]: за [{}] ms выполнен {}.{}",
				guid,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}
}
