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
 * @author shibaev.aleksey 30.03.2023
 */
@Component
@Aspect
@Slf4j
public class PerformanceLogAspect {

	@Pointcut("@annotation(PerformanceLogAnnotation)")
	public void ifPerformanceLogAnnotation() {
	}

	@SneakyThrows
	@Around("ifPerformanceLogAnnotation()")
	public Object ifPerformanceLogAnnotationAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		if (log.isDebugEnabled()) {
			Stopwatch stopwatch = Stopwatch.createStarted();
			UUID transactionalID = UUID.randomUUID();
			Signature signature = proceedingJoinPoint.getSignature();
			doLogBefore(transactionalID, signature);

			Object proceed = proceedingJoinPoint.proceed();

			stopwatch.stop();
			doLogAfter(stopwatch, transactionalID, signature, proceed);
			return proceed;
		} else {
			return proceedingJoinPoint.proceed();
		}
	}

	private void doLogAfter(Stopwatch stopwatch, UUID operationId, Signature signature, Object proceed) {
		if (proceed instanceof Collection) {
			doCollectionPerformanceLogging(stopwatch, operationId, signature, (Collection<?>) proceed);
		} else {
			doDefaultPerformanceLogging(stopwatch, operationId, signature);
		}
	}

	private void doLogBefore(UUID transactionalID, Signature signature) {
		log.debug("[{}]: запущен {}.{}",
				transactionalID,
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doCollectionPerformanceLogging(Stopwatch stopwatch, UUID operationId, Signature signature, Collection<?> proceed) {
		log.debug("[{}]: за [{}] ms вернул [{}] шт.  выполнен {}.{}",
				operationId,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				proceed.size(),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doDefaultPerformanceLogging(Stopwatch stopwatch, UUID operationId, Signature signature) {
		log.debug("[{}]: за [{}] ms выполнен {}.{}",
				operationId,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}
}
