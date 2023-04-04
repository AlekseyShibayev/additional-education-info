package com.company.app.core.aop.logging;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Аспект, логирует разными способами производительность метода.
 * Метод должен быть помечен аннотацией @PerformanceLogAnnotation
 * <p>
 * UUID берется из первого параметра сигнатуры метода, либо генерируется рандомный.
 * <p>
 * Способы логирования:
 * 1. Стандартный
 * PerformanceLogAspect] [] [] [76069a35-dab7-45bf-968e-a24281ac5a21]: запущен com.company.app.exchangeRate.ExchangeRateFacade.extract
 * PerformanceLogAspect] [] [] [76069a35-dab7-45bf-968e-a24281ac5a21]: за [1757] ms выполнен com.company.app.exchangeRate.ExchangeRateFacade.extract
 * <p>
 * 2. Для Collection
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
			String operationId = getOperationId(proceedingJoinPoint);
			doLogBefore(operationId, signature);

			Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

			stopwatch.stop();
			doLogAfter(stopwatch, operationId, signature, proceed);
			return proceed;
		} else {
			return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		}
	}

	private String getOperationId(ProceedingJoinPoint proceedingJoinPoint) {
		try {
			Object[] args = proceedingJoinPoint.getArgs();
			UUID uuid = UUID.fromString(String.valueOf(args[0]));
			return uuid.toString();
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
	}

	private void doLogAfter(Stopwatch stopwatch, String operationId, Signature signature, Object proceed) {
		if (proceed instanceof Collection) {
			doCollectionPerformanceLogging(stopwatch, operationId, signature, (Collection<?>) proceed);
		} else {
			doDefaultPerformanceLogging(stopwatch, operationId, signature);
		}
	}

	private void doLogBefore(String operationId, Signature signature) {
		log.debug("[{}]: запущен {}.{}",
				operationId,
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doCollectionPerformanceLogging(Stopwatch stopwatch, String operationId, Signature signature, Collection<?> proceed) {
		log.debug("[{}]: за [{}] ms вернул [{}] шт. выполнен {}.{}",
				operationId,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				proceed.size(),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doDefaultPerformanceLogging(Stopwatch stopwatch, String operationId, Signature signature) {
		log.debug("[{}]: за [{}] ms выполнен {}.{}",
				operationId,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}
}
