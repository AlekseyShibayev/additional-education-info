package com.company.app.springboot.application;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;

/**
 * @SpringBootTest: 1. Без classes. Идёт к корню приложения, ничего не считывает, ищет @SpringBootConfiguration
 * которая, обычно есть в Application.class в @SpringBootApplication
 * когда нашёл - выполняет её, т.е. поднимает весь спринг бут контекст
 * после идет обратно и считывает конфигурации для тестов.
 * 2. classes = @Configuration. Поднимет только указанные конфигурации.
 * 3. classes = @TestConfiguration. Поднимет указанный контекст и продолжит сканирование (см. п. 1).
 * @DataJpaTest - тоже что и @SpringBootTest, но создает только @Repository
 * Можно сделать @Autowired TestEntityManager
 * @WebMvcTest - для усечения контекста.
 * Тоже что и @SpringBootTest, но создает только @Controller и прочие штуки (but not @Component, @Service or @Repository beans).
 * Можно сделать @Autowired MockMvc
 * @Configuration - в тестах не использовать, может перезаписаться мапа бинов, если id в разном реестре.
 * @SpringBootConfiguration - в тестах в корне пакета можно сделать стоппер, StopСonfiguration.class - для прекращения сканирования @SpringBootTest
 * @TestConfiguration - если нужно не прерывать сканирование @SpringBootTest и скормить ему тестовый конфиг.
 * @MockBean - спринговый mock, инжект филда или аннотация класса.
 * @RunWith(SpringRunner.class) - для движка JUnit4.
 * @ExtendWith(SpringExtension.class) - для движка JUnit5, сейчас не стоит, т.к. она есть в @SpringBootTest.
 */

@Slf4j
@ExtendWith(OutputCaptureExtension.class)
@TestPropertySource("/test.properties")
@SpringBootTest(
		classes = ApplicationSpringBootTestConfiguration.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class ApplicationSpringBootTestContext {

	@PostConstruct
	void init() {
		log.debug("**********     запущена группа тестов всего приложения     **********");
	}
}
