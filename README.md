# TelegramNotificationService

Практическое применение бота:
1. Показывает курс доллара aliexpress.
2. Следит за ценами заданных товаров wildberries.
3. Пишет что угодно мне в телегу.

Теоретическое применение бота:
1. Щупаю технологии SpringBoot, SpringData, SpringScheduling, SpringCaching, SpringAop, SpringBootTest.
2. Бд использую H2, потому что давно хотел. Вот консоль её: http://localhost:8080/h2-console
3. Есть swagger: http://localhost:8080/swagger-ui.html

Вечно забываемые, команды docker:
1. docker build --tag "app:0" .
2. docker run -d -p 8080:8080 -p 5005:5005 app:0
3. docker stop

Todo'шки:
1. Разбить проект на 4 подпроекта. 
2. Подпроект core должен подключаться в каждый, и использовать магию спринга.
3. Добавить docker-compose
4. Добавить wildberries чтение из google doc