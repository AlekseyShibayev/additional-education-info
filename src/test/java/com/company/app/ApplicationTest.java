package com.company.app;

import com.company.app.exchangerate.scheduler.ExchangeRateSchedulerConfig;
import com.company.app.wildberries.scheduler.WildberriesSchedulerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(ExchangeRateSchedulerConfig.class)
@MockBean(WildberriesSchedulerConfig.class)
public abstract class ApplicationTest {
}
