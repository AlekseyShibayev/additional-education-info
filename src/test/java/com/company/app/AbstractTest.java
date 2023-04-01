package com.company.app;

import com.company.app.exchangeRate.scheduler.ExchangeRateSchedulerConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(ExchangeRateSchedulerConfig.class)
public abstract class AbstractTest {
}
