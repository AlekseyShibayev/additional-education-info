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

import java.lang.reflect.Field;
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
 * 1.В аннотацию можно передать параметры. Пример @PerformanceLogAnnotation(number = "0", fieldName = "guid")
 *  number - порядковый номер объекта в сигнатуре метода, начинается с 0.
 *  fieldName - поле объекта, содержащее UUID.
 * 2. Аннотация без параметров. Тогда гуид попытается вытащить из первого объекта в сигнатуре.
 * 3. Если в п.1 или п.2 упали - сгенерируется рандомный гуид.
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
			String number = annotation.number();

			if (StringUtils.isNotEmpty(number)) {
				int i = Integer.parseInt(annotation.number());
				Object arg = proceedingJoinPoint.getArgs()[i];
				Field field = recursiveFieldSearch(arg.getClass(), annotation.fieldName());
				field.trySetAccessible();
				Object object = field.get(arg);
				UUID uuid = UUID.fromString(String.valueOf(object));
				return uuid.toString();
			} else {
				Object[] args = proceedingJoinPoint.getArgs();
				UUID uuid = UUID.fromString(String.valueOf(args[0]));
				return uuid.toString();
			}
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		} finally {
			stopwatch.stop();
			log.trace("выковыривание guid заняло [{}] ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
		}
	}

	@SneakyThrows
	private <T> Field recursiveFieldSearch(Class<T> clazz, String fieldName) {
		List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
				.filter(field -> field.getName().equals(fieldName))
				.collect(Collectors.toList());
		return CollectionUtils.isEmpty(fields) ? recursiveFieldSearch(clazz.getSuperclass(), fieldName) : fields.get(0);
	}

	private Method getMethod(Signature signature) {
		for (Method method : signature.getDeclaringType().getDeclaredMethods()) {
			if ((method.getName().equals(signature.getName()) && method.isAnnotationPresent(PerformanceLogAnnotation.class)))
				return method;
		}
		throw new RuntimeException("этого никогда не случится");
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
