package com.company.app.core.aop.logging.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Методы для логирования.
 *
 * @author shibaev.aleksey 27.12.2022
 */
@Slf4j
public class LogUtils {

	private LogUtils() {
		throw new UnsupportedOperationException();
	}

	public static void doExceptionLog(Throwable e, String message) {
		log.error(message, e.getMessage(), e);
	}

	public static void doExceptionLog(Throwable e) {
		log.error(e.getMessage(), e);
	}
}
