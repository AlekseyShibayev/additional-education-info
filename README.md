# TelegramNotificationService

Такс, тут я бота пишу, не простого, а:
1. Бот уже умеет курс алишки вытаскивать.
2. Писать его в телегу, само собой.
3. Щупаю технологии SpringBoot, SpringData, SpringScheduling, SpringCaching.
4. Бд использую H2, потому что давно хотел. Вот консоль её: http://localhost:9001/h2-console
5. Развлекаюсь со @SpringBootTest.
6. //todo логирование пощупать через аспекты.

Ниже команды удобные и инфа всякая, просто чтобы под рукой была:
docker build --tag "bot:0" .
docker run -p 8080:8080 -p 5005:5005 bot:0

default for postman test is: 
http://localhost:8080 or http://127.0.0.1:8080

docker save -o mf.tar mf:0

