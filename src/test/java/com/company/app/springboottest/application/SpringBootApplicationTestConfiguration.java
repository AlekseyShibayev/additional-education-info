package com.company.app.springboottest.application;

import com.company.app.exchangerate.scheduler.ExchangeRateSchedulerConfig;
import com.company.app.wildberries.scheduler.WildberriesSchedulerConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
@MockBean(ExchangeRateSchedulerConfig.class)
@MockBean(WildberriesSchedulerConfig.class)
public class SpringBootApplicationTestConfiguration {}
