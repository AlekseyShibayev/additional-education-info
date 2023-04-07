package com.company.app;

import com.company.app.exchangerate.scheduler.ExchangeRateSchedulerConfig;
import com.company.app.wildberries.scheduler.WildberriesSchedulerConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * в Junit5 @ExtendWith(SpringExtension.class) - сейчас не стоит, т.к. она есть в @SpringBootTest
 * в Junit4 @RunWith(SpringRunner.class)
 * использую Junit5: @ExtendWith(SpringExtension.class) - сейчас не стоит, т.к. она есть в @SpringBootTest
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(ExchangeRateSchedulerConfig.class)
@MockBean(WildberriesSchedulerConfig.class)
public abstract class ApplicationTest {
}
