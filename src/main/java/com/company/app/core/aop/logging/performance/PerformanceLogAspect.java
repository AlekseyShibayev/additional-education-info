package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.component.ActionType;
import com.company.app.core.aop.logging.performance.component.api.GuidExtractor;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Аспект, логирует разными способами производительность метода, который помечен {@link PerformanceLogAnnotation}
 * <p>
 * Способы логирования:
 * <ul>
 * <li> 1. Стандартный, пример:
 * <li> [76069a35-dab7-45bf-968e-a24281ac5a21]: запущен com.company.app.exchangeRate.ExchangeRateFacade.extract
 * <li> [76069a35-dab7-45bf-968e-a24281ac5a21]: за [1757] ms выполнен com.company.app.exchangeRate.ExchangeRateFacade.extract
 * <li> 2. Для Collection - пишет размер, пример:
 * <li> [42db76b0-f2e2-4994-9b12-6fa603e62e72]: запущен com.company.app.wildberries.component.WildberriesFacade.getDesiredLots
 * <li> [42db76b0-f2e2-4994-9b12-6fa603e62e72]: за [351] ms вернул [0] шт. выполнен com.company.app.wildberries.component.WildberriesFacade.getDesiredLots
 * </ul>
 * ВАЖНО!!!
 * Для получения GUID - не надо переписывать сигнатуры существующих методов.
 * Аспект расковыривает GUID сам.
 * Способы получения GUID:
 * {@link ActionType}
 * По дефолту - RANDOM.
 * Если GUID вытащить не может - используется RANDOM. Для поиска причин: <logger name=".*.GuidExtractorImpl" level="TRACE" additivity="false">
 * <p>
 * Аспект будет создан, если application.performanceLogAnnotation=true
 * Логирование будет, если <logger name=".*.PerformanceLogAspect" level="DEBUG" additivity="false">
 * <p>
 * @author shibaev.aleksey 30.03.2023
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = "application", name = "performanceLogAnnotation")
public class PerformanceLogAspect {

	@Autowired
	GuidExtractor guidExtractor;

	@Pointcut("@annotation(PerformanceLogAnnotation)")
	public void ifPerformanceLogAnnotation() {
	}

	@SneakyThrows
	@Around("ifPerformanceLogAnnotation()")
	public Object ifPerformanceLogAnnotationAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		if (log.isDebugEnabled()) {
			Stopwatch stopwatch = Stopwatch.createStarted();
			Signature signature = proceedingJoinPoint.getSignature();
			String guid = guidExtractor.extractGuid(proceedingJoinPoint);
			doLogBefore(guid, signature);

			Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

			stopwatch.stop();
			doLogAfter(stopwatch, guid, signature, proceed);
			return proceed;
		} else {
			return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		}
	}

	private void doLogAfter(Stopwatch stopwatch, String guid, Signature signature, Object proceed) {
		if (proceed instanceof Collection) {
			doCollectionPerformanceLog(stopwatch, guid, signature, (Collection<?>) proceed);
		} else {
			doDefaultPerformanceLog(stopwatch, guid, signature);
		}
	}

	private void doLogBefore(String guid, Signature signature) {
		log.debug("[{}]: запущен {}.{}",
				guid,
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doCollectionPerformanceLog(Stopwatch stopwatch, String guid, Signature signature, Collection<?> proceed) {
		log.debug("[{}]: за [{}] ms вернул [{}] шт. выполнен {}.{}",
				guid,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				proceed.size(),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}

	private void doDefaultPerformanceLog(Stopwatch stopwatch, String guid, Signature signature) {
		log.debug("[{}]: за [{}] ms выполнен {}.{}",
				guid,
				stopwatch.elapsed(TimeUnit.MILLISECONDS),
				signature.getDeclaringType().getName(),
				signature.getName()
		);
	}
}
