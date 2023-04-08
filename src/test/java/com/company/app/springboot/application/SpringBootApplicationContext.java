package com.company.app.springboot.application;

import com.company.app.exchangerate.scheduler.ExchangeRateSchedulerConfig;
import com.company.app.wildberries.scheduler.WildberriesSchedulerConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @SpringBootTest - имеет 2 реализации
 * 1. Если передали конфигурацию, то делает что в ней.
 * 2. Идёт к корню приложения, ничего не считывает, ищет @SpringBootConfiguration
 * которая, обычно есть в Application.class в @SpringBootApplication
 * когда нашёл - выполняет её, т.е. поднимает весь спринг бут контекст
 * после идет обратно и считывает конфигурации для тестов.
 *
 * @DataJpaTest - тоже что и @SpringBootTest, но создает только @Repository
 * Можно сделать @Autowired TestEntityManager
 *
 * @WebMvcTest - для усечения контекста.
 * Тоже что и @SpringBootTest, но создает только @Controller и прочие штуки (but not @Component, @Service or @Repository beans).
 * Можно сделать @Autowired MockMvc
 *
 * @Configuration - в тестах не использовать, может перезаписаться мапа бинов, если id в разном реестре.
 * @SpringBootConfiguration - в тестах в корне пакета можно сделать стоппер, StopСonfiguration.class - для прекращения сканирования @SpringBootTest
 * @TestConfiguration - если нужно не прерывать сканирование @SpringBootTest и скормить ему тестовый конфиг.
 *
 * @MockBean - спринговый mock, инжект филда или аннотация класса.
 *
 * @RunWith(SpringRunner.class) - для движка JUnit4.
 * @ExtendWith(SpringExtension.class) - для движка JUnit5, сейчас не стоит, т.к. она есть в @SpringBootTest.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(ExchangeRateSchedulerConfig.class)
@MockBean(WildberriesSchedulerConfig.class)
public abstract class SpringBootApplicationContext {
}
