package com.company.app;

import com.company.app.exchange_rate.scheduler.ExchangeRateSchedulerConfig;
import com.company.app.wildberries.scheduler.WildberriesSchedulerConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(ExchangeRateSchedulerConfig.class)
@MockBean(WildberriesSchedulerConfig.class)
public abstract class ApplicationTest {
}
