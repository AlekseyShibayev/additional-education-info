package com.company.app;

import com.company.app.exchangerate.scheduler.ExchangeRateSchedulerConfig;
import com.company.app.wildberries.scheduler.WildberriesSchedulerConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @SpringBootTest - сканирует вверх по пакету, ищет @SpringBootApplication, нашел, идёт вниз.
 *
 * @SpringBootConfiguration - в тестах делаем стоппер, в корне пакета, как Application.class - StopСonfiguration.class
 * @TestConfiguration - если нужно не прерывать сканирование @SpringBootTest
 * @Configuration - в тестах не использовать, может перезаписаться мапа бинов, если id в разном реестре.
 *
 * @DataJpaTest и @WebMvcTest - для усечения контекста. @Autowired MockMvc
 * @MockBean - спринговый mock, инжект филда или аннотация класса.
 *
 * @RunWith(SpringRunner.class) - для движка JUnit4
 * @ExtendWith(SpringExtension.class) - для движка JUnit5, сейчас не стоит, т.к. она есть в @SpringBootTest.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(ExchangeRateSchedulerConfig.class)
@MockBean(WildberriesSchedulerConfig.class)
public abstract class AbstractSpringBootTest {
}
