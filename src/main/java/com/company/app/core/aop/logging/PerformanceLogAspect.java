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

			log.debug("[{}]: запущен {}.{}",
					transactionalID,
					signature.getDeclaringType().getName(),
					signature.getName()
			);

			Object proceed = proceedingJoinPoint.proceed();

			stopwatch.stop();
			log.debug("[{}]: за [{}] ms выполнен {}.{}",
					transactionalID,
					stopwatch.elapsed(TimeUnit.MILLISECONDS),
					signature.getDeclaringType().getName(),
					signature.getName()
			);
			return proceed;
		} else {
			return proceedingJoinPoint.proceed();
		}
	}
}
