# TelegramNotificationService

Практическое применение бота:
1. Показывает курс доллара aliexpress.
2. Следит за ценами заданных товаров wildberries.
3. Пишет что угодно мне в телегу.

Теоретическое применение бота:
1. Щупаю технологии Spring (Core, Boot, Data, Web, Caching, Scheduling, AOP, Boot Test).
2. Бд использую H2, потому что давно хотел. Вот консоль её: http://localhost:8080/h2-console
3. Есть swagger: http://localhost:8080/swagger-ui.html

Вечно забываемые, команды docker:
1. docker build --tag "app:0" .
2. docker save -o app.tar app:0
3. docker run -d -p 8080:8080 -p 5005:5005 app:0
4. docker stop

Маленькая jira (todo'шки):
- написать нормальный юнит тест, для PerformanceLogAspect, перехватив сообщение из логгера и проверив, что в нем есть нужный гуид

- найти или сделать Dockerfile openjre_alpine-musl, выкинув не нужные пакеты из jre, ожидаемый вес < 80 mb

- вынести core в отдельный проект, который остальные проекты должны подключать, при этом @Component из core должны работать
- разбить оставшийся проект на 3 модуля, научить общаться их между собой
- засунуть 3 проекта + core в docker-compose
- сделать общение через kafka

- в core, сделать админку для сброса спринг кеш

- посмотреть io.micrometer.core.annotation.Timed io.micrometer.core.aop.TimedAspect