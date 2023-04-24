# Учебно-прикладной проект TelegramNotificationService

  * [Ссылка на себя](https://github.com/AlekseyShibayev/TelegramNotificationService)
  * Проект использует: [telegram-bot-spring-boot-starter](https://github.com/AlekseyShibayev/telegram-bot-spring-boot-starter)
  * Ссылка на различные обучающие курсы: [TODO]

## Цели проекта:
### 1. Учебная:
1. Получить продвинутые навыки в разработке Java + Spring Framework приложений:
  * Java Core: Java 11, Stream API, Lambdas, Optional, Reflection
  * Spring(Core, Boot, Data Jpa, Web, Caching, Scheduling, AOP)
  * Spring Boot Test, JUnit5
  * Refactoring, Patterns, SOLID, DRY, KISS
2. Изучить технологии:
  * Spring Security
  * Docker Compose, Kubernetes, TestContainers
  * Kafka

### 2. Прикладная:
1. Написать приложение c использованием микросервисной архитектуры.
2. Развернуть сервер на одноплатнике Orange Pi 4.
3. Пользоваться благами написанных модулей.
   
## Что уже есть в проекте:
### Модули:
  * модуль core - содержит удобные штуковины, которые будут нужны в каждом микросервисе (вынесены в [telegram-bot-spring-boot-starter](https://github.com/AlekseyShibayev/telegram-bot-spring-boot-starter))
  * модуль telegram - управляет подписками
  * модуль exchangerate - отслеживает курс доллара aliexpress
  * модуль wildberries - отслеживает желаемые лоты
### Технологии:
  * java 11 + spring boot + maven
  * бд использую H2, потому что давно хотел. Вот консоль её: http://localhost:8080/h2-console
  * swagger: http://localhost:8080/swagger-ui.html
  * есть возможность запуска в Docker
  * перешел с JUnit4 на JUnit5

## Вечно забываемые команды docker:
1. docker build --tag "app:0" .
2. docker save -o app.tar app:0
3. docker run -d -p 8080:8080 -p 5005:5005 app:0
4. docker stop {$name}

## Маленькая jira (todo'шки, мысли):
1. Простые:

  - использовать бд PostgreSQL, H2 использовать в тестах
  - прикрутить flyway, делать DDL не авто, а скриптами

  - добавить @Validate

  - посмотреть что там в современном апи ТГ, надо как-то добавление Lot добавить

2. Сложные:
  - разбить оставшийся проект на 3 модуля, научить общаться их между собой
  - засунуть 3 проекта + core в docker-compose
  - сделать общение через kafka
  - засунуть 3 проекта (с общим core) + kafka в kuber
  - прикрутить TestContainers или WireMock

3. Прочие:
  - посмотреть io.micrometer.core.annotation.Timed io.micrometer.core.aop.TimedAspect
  - найти или сделать Dockerfile openjre_alpine-musl, выкинув не нужные пакеты из jre, ожидаемый вес < 80 mb [habr](https://habr.com/ru/companies/piter/articles/692992/)
  - в core, сделать админку для сброса спринг кеш
  - прикрутить kafka + ELK
  - посмотреть Debezium(конфиги) + Redis(кеш)

## Список полезной информации (не вся - только та что понравилась) для потомков, скопившейся за N лет разработки:

* Java Core

[Теория, просто включаем фоном и слушаем](https://www.youtube.com/watch?v=9GdtWiovvIQ&list=PLmqFxxywkatR3qNmxqcFIHF9MN2-_eteU)
 
[Краткие примеры основных структур языка](https://www.youtube.com/watch?v=9GdtWiovvIQ&list=PLmqFxxywkatR3qNmxqcFIHF9MN2-_eteU)

[Очень подробный просмотр основных структур языка. Выбираем любой понравившийся плейлист JavaCore](https://www.youtube.com/user/KharkovITCourses/playlists?view=50&sort=dd&shelf_id=12)

* Data Base + SQL

[Плейлист (канал Аве Кодер)](https://www.youtube.com/watch?v=PfyC39EzTmk&list=PLPPIc-4tm3YQsdhSV1qzAgDKTuMUNnPmp&index=3&ab_channel=%D0%90%D0%B2%D0%B5%D0%9A%D0%BE%D0%B4%D0%B5%D1%80)

* Spring Core

[Базовый уровень, для тех кто слышит про Spring впервые](https://www.youtube.com/watch?v=6mXTY7RSAf0&ab_channel=%D0%9E%D0%BD%D0%BB%D0%B0%D0%B9%D0%BD%D1%83%D1%80%D0%BE%D0%BA%D0%B8%D0%BF%D0%BEJava)

[Средний уровень](https://www.youtube.com/watch?v=5ePo08sqcpk&list=PLAma_mKffTOR5o0WNHnY0mTjKxnCgSXrZ&ab_channel=alishev)

[Продвинутый уровень](https://www.youtube.com/watch?v=BmBr5diz8WA&ab_channel=JPoint%2CJoker%D0%B8JUGru)

[Дополнительно: эволюция spring core. Та самая инфа, которая заполнит пробел, появившийся между java core и использованием spring di/ioc](https://www.youtube.com/watch?v=rd6wxPzXQvo&ab_channel=JPoint%2CJoker%D0%B8JUGru)

* Spring Data, JPA, Hibernate

[Базовый уровень. Делаем hbm в xml. Плейлист](https://www.youtube.com/watch?v=VQPoe2OVghQ&list=PL7Bt6mWpiiza3rrRXmMwWdsQFDlmF2bWQ&index=4&ab_channel=JavaVision)

[Немного теории](https://www.youtube.com/watch?v=C-wEZjEOhWc&list=LL&index=4&t=2224s&ab_channel=JPoint%2CJoker%D0%B8JUGru)

[Spring Data puzzler](https://www.youtube.com/watch?v=o8LN6NOFa3c&t=2127s&ab_channel=JPoint%2CJoker%D0%B8JUGru)

* Java Core Advanced

[Многоядерное программирование на Java Плейлист](https://www.youtube.com/watch?v=4YGqahTTWtQ&list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&index=2&ab_channel=GolovachCourses)

[Lambdas](https://www.youtube.com/watch?v=hqRVz_4wCrc&list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&index=10&ab_channel=GolovachCourses)

[Stream API](https://www.youtube.com/watch?v=D4CScx_4xUg&list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&index=11&ab_channel=GolovachCourses)

* Refactoring

[Обзорная лекция по книге Clean Code](https://www.youtube.com/watch?v=otrfSgeK3JI&ab_channel=SergeyNemchinskiy)

[Что-то древнее от Немчинского, уже не вспомню годное или нет. Плейлист](https://www.youtube.com/watch?v=j38-ZSyOAvc&list=PLmqFxxywkatR5zj5M4WdUyyKyLoJSZZrQ&ab_channel=SergeyNemchinskiy)

[Шаблоны проектирования. Плейлист](https://www.youtube.com/watch?v=k6oh9C_71mE&list=PLlsMRoVt5sTPgGbinwOVnaF1mxNeLAD7P&ab_channel=EugeneSuleimanov)

[SOLID. Теория. Плейлист](https://www.youtube.com/watch?v=O4uhPCEDzSo&list=PLmqFxxywkatQNWLG1IZYUhKoQrnuZHqaK&ab_channel=SergeyNemchinskiy)

[SOLID. Примеры](https://www.youtube.com/watch?v=StWB7NJjPZc&ab_channel=TechTrain)

[Евгений Борисов — Spring Patterns](https://www.youtube.com/watch?v=61duchvKI6o&ab_channel=TechTrain)

[Spring Patterns (Evgeny Borisov, Israel)](https://www.youtube.com/watch?v=zLFgvdHUlA0&ab_channel=jeeconf)

[Евгений Борисов — Spring Patterns для взрослых](https://www.youtube.com/watch?v=GL1txFxswHA&t=2936s&ab_channel=JPoint%2CJoker%D0%B8JUGru)

* Spring Boot

[Как мы жили до Spring Boot. Плейлист](https://www.youtube.com/watch?v=P_W3NbkwdIM&list=PLVJtKDGxOX1V8NpyHUAkrdezZDvgDhe4b&ab_channel=FIXGroupofCompanies)

[Spring Boot - Евгений Борисов, Кирилл Толкачев](https://www.youtube.com/watch?v=UYre4_bytD4&t=3345s&ab_channel=JPoint%2CJoker%D0%B8JUGru)

[Spring Cache](https://habr.com/ru/articles/465667/)

[Spring AOP. Плейлист](https://www.youtube.com/watch?v=IDVHzrreYU4&list=PLqj7-hRTFl_p-t5F2zSUlG6_9UIoE2r70&index=25&ab_channel=%D0%9F%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%B0%D0%BD%D0%B8%D1%8F)

* Test

[AspectJ](https://habr.com/ru/articles/675778/)

[Spring Boot Test](https://www.youtube.com/watch?v=uc-cfX-5wQA&ab_channel=Heisenbug)

[Spring Boot Test](https://www.youtube.com/watch?v=7mZqJShu_3c&ab_channel=JPoint%2CJoker%D0%B8JUGru)

* Docker

[Основы. Теория](https://www.youtube.com/watch?v=QF4ZF857m44&t=1700s&ab_channel=%D0%90%D1%80%D1%82%D0%B5%D0%BC%D0%9C%D0%B0%D1%82%D1%8F%D1%88%D0%BE%D0%B2)

[Основы. Практика. Плейлист](https://www.youtube.com/watch?v=Sa7uOGczoHc&list=PLU2ftbIeotGoGFC_2lj-OplT_cItXfu48&ab_channel=letsCode)

* CI/CD

[Базовый уровень. Теория](https://www.youtube.com/watch?v=7SM8GLArTDY&ab_channel=KirillSemaev)

[Базовый уровень. GitLab](https://www.youtube.com/watch?v=G1CeagPCEIk&ab_channel=ITVDN)

* Gradle

[Евгений Борисов — Power of Gradle](https://www.youtube.com/watch?v=NZJTYPLb0iE&ab_channel=JPoint%2CJoker%D0%B8JUGru)

* Интеграция (Kafka)

[Как мы жили до Kafka](https://www.youtube.com/watch?v=p45WDeEky_o&ab_channel=SergeyNemchinskiy)

[Теория](https://www.youtube.com/watch?v=-AZOi3kP9Js&ab_channel=%D0%92%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80%D0%91%D0%BE%D0%B3%D0%B4%D0%B0%D0%BD%D0%BE%D0%B2%D1%81%D0%BA%D0%B8%D0%B9)

[Spring Boot + Kafka](https://habr.com/ru/articles/440400/)

* Прочее

[Про ООМы, heap dumpы и как это анализировать](https://www.youtube.com/watch?v=t_-WyfS9a7k&ab_channel=%D0%A1odeFreezeVideo)

* Еще не смотрел, но надо бы:

[микросервисы](https://www.youtube.com/watch?v=bAhxpqHfP8I&t=1915s&ab_channel=AvitoTech)

[микросервисы еще](https://www.youtube.com/watch?v=6HvSpqBc8fA&ab_channel=HighLoadChannel)

[и ещё](https://habr.com/ru/companies/avito/articles/426101/)

[Spring Boot + Kafka](https://habr.com/ru/articles/440400/)

select * from CHAT;
select * from HISTORY;
select * from USER_INFO;
select * from CHATS_SUBSCRIPTIONS;
select * from SUBSCRIPTION;
select * from LOT;
select * from EXCHANGE_RATE;