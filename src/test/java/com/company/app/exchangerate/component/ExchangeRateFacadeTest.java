package com.company.app.exchangerate.component;

import com.company.app.exchangerate.ExchangeRateTestConfiguration;
import com.company.app.exchangerate.entity.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Component Test - без поднятия всего спринг контекста приложения.
 * <p>
 * Алгоритм работы:
 * 1. @SpringBootTest(classes = ExchangeRateTestConfiguration.class) подхватывает @TestConfiguration
 * 2. Создает кусок тест спринг контекста из ExchangeRateTestConfiguration.class
 * в котором 2 мока и 1 бин (из внешнего пакета).
 * 2. Запускается @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * 3. Идет сканирование по папкам к корню, ищет @SpringBootConfiguration
 * 4. Находит его в SpringBootTestContextBootstrapperStopper.class
 * 5. Запускает у него @ComponentScan(basePackages = "com.company.app.exchangerate.component")
 * 6. Сканирует все бины в test и main, т.к. они в тестах слиты.
 * 7. ...
 * 8. Инжектит мне @Autowired ExchangeRateFacade exchangeRateFacade;
 * <p>
 * @ComponentScan(lazyInit = true) - уменьшит количество Mock
 */
@Slf4j
@SpringBootTest(classes = ExchangeRateTestConfiguration.class)
class ExchangeRateFacadeTest {

	@Autowired
	private ExchangeRateFacade exchangeRateFacade;

	@Test
	void extractCurse() {
		log.debug("**********     This is component test: [{}].     **********", this.getClass());
		ExchangeRate exchangeRate = exchangeRateFacade.extract();
		Assertions.assertNotNull(exchangeRate.getAliexpressExchangeRate());
	}
}