# Учебно-практический проект TelegramNotificationService
https://github.com/AlekseyShibayev/TelegramNotificationService


Цель проекта:
1. фыв

Учебная цель проекта:
1.

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
- пощупать AssertJ https://habr.com/ru/articles/675778/

- найти или сделать Dockerfile openjre_alpine-musl, выкинув не нужные пакеты из jre, ожидаемый вес < 80 mb

- вынести core в отдельный проект, который остальные проекты должны подключать, при этом @Component из core должны работать
- разбить оставшийся проект на 3 модуля, научить общаться их между собой
- засунуть 3 проекта + core в docker-compose
- сделать общение через kafka

- в core, сделать админку для сброса спринг кеш

- посмотреть io.micrometer.core.annotation.Timed io.micrometer.core.aop.TimedAspect

Список полезной информации для потомков, скопившийся за Х лет разработки:
1. Java Core:
<a href="https://www.youtube.com/watch?v=9GdtWiovvIQ&list=PLmqFxxywkatR3qNmxqcFIHF9MN2-_eteU">
   Теория, просто включаем фоном и слушаем.
</a>
<a href="https://www.youtube.com/watch?v=9GdtWiovvIQ&list=PLmqFxxywkatR3qNmxqcFIHF9MN2-_eteU">
   Краткие примеры основных структур языка.
</a>
<a href="https://www.youtube.com/user/KharkovITCourses/playlists?view=50&sort=dd&shelf_id=12">
   Очень подробный просмотр основных структур языка. Выбираем любой понравившийся плейлист JavaCore.
</a>
2. Data Base + SQL:
<a href=" https://www.youtube.com/watch?v=PfyC39EzTmk&list=PLPPIc-4tm3YQsdhSV1qzAgDKTuMUNnPmp&index=3&ab_channel=%D0%90%D0%B2%D0%B5%D0%9A%D0%BE%D0%B4%D0%B5%D1%80">
   Плейлист (канал Аве Кодер)
</a>
3. Spring Core:
<a href="https://www.youtube.com/watch?v=6mXTY7RSAf0&ab_channel=%D0%9E%D0%BD%D0%BB%D0%B0%D0%B9%D0%BD%D1%83%D1%80%D0%BE%D0%BA%D0%B8%D0%BF%D0%BEJava">
   Базовый уровень, для тех кто слышит про Spring впервые
</a>


<a href="">

</a>