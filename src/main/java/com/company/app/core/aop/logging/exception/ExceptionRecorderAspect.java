package com.company.app.core.aop.logging.exception;

import com.company.app.core.aop.logging.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Аспект, который пишет в ERROR любой Throwable для endpoint'ов приложения.
 *
 * @author shibaev.aleksey 31.03.2023
 */
@Slf4j
@Aspect
@Component
public class ExceptionRecorderAspect {

	@Pointcut("execution(* com.company.app.exchangeRate.scheduler.*.*(..))")
	public void ifExchangeRateScheduler() {
	}

	@Pointcut("execution(* com.company.app.wildberries.scheduler.*.*(..))")
	public void ifWildberriesScheduler() {
	}

	@Pointcut("execution(* com.company.app.wildberries.controller.*.*(..))")
	public void ifWildberriesController() {
	}

	@Pointcut("ifExchangeRateScheduler() || ifWildberriesScheduler() || ifWildberriesController()")
	public void ifEndpointsMethod() {
	}

	@Around("ifEndpointsMethod()")
	public Object recordExceptionAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
		} catch (Throwable throwable) {
			LogUtils.doExceptionLog(throwable, getMessage(proceedingJoinPoint.getSignature()));
			throw throwable;
		}
	}

	@NotNull
	private String getMessage(Signature signature) {
		return "Error from: " + signature.getDeclaringType().getName() + "." + signature.getName();
	}
}
