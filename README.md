# HSE-HW-4: Online Store
## Асинхронное межсервисное взаимодействие


### Начало работы
```bash
 docker-compose up --build   
```
или
``` bash
docker-compose build
docker-compose up
```
### Swagger-UI
http://localhost:8083/swagger-ui.html

### Фронтенд
http://localhost:3000

### Завершение работы
``` bash
docker-compose down
```
## Документация:
Вся документация доступна через `api-gateway`

**Swagger-ui для двух микросервисов:** 

http://localhost:8083/swagger-ui.html

**Отдельная документация для каждого микросервиса:**

http://localhost:8083/api/order-service/v3/api-docs

http://localhost:8083/api/payment-service/v3/api-docs

## Фронтенд
Доступен по адресу: http://localhost:3000

Фронтенд предоставляет веб-интерфейс для взаимодействия с микросервисами, охватывая всю их функциональность.

Фронтенд реализован в отдельном сервисе `frontend` на **React** с использованием **Vite**.
Для построения пользовательского интерфейса используется библиотека **MUI**. Обмен данными с backend-сервисами
осуществляется по **REST API** с помощью **Axios** через `api-gateway`.  Обработка ошибок выполнена централизованно с отображением сообщений пользователю.

Для доступа к веб-интерфейсу достаточно лишь выполнить `docker-compose up --build` и перейти по ссылочке.

## Архитектура
Разработана микросервисная архитектура серверной части веб-приложения с использованием Spring Boot и RabbitMQ:
1. **Order Service** – `8081` - отвечает за работу с заказами.
   - POST `api/order-service/order/{userId}/{amount}/{description}` добавить заказ по айди юзера, сумме и описанию
   - GET `api/order-service/orders` просмотреть список всех заказов
   - GET `api/order-service/{id}/status` просмотреть статус заказа по его айди
2. **Payment Service** – `8082` - отвечает за работу со счетами и платежами.
   - POST `/api/payment-service/{userId}/account` добавить новый счет по айди юзера
   - PATCH `/api/payment-service/{id}/top-up/{sum}/account` пополнить счет по его айди на определенную сумму
   - POST `/api/payment-service/{id}/balance` получить баланс счёта по его айди
   - GET `/api/payment-service/accounts` получить список всех счетов
3. **API Gateway** - `8083`- центральный сервис-посредник, принимающий запросы от клиентов и маршрутизирующий их к соответствующим микросервисам. 
4. **Frontend** - `3000` - отдельный сервис для веб-интерфейса

## RabbitMQ
`RabbitMQ` обеспечивает at-least-once, а exactly-once достигается благодаря проверке полученных сообщений на уникальность,
 обеспечивающей идемпотентность бизнес-операций.

## Docker
Все микросервисы и БД упакованы в Docker-контейнеры, что позволяет развернуть приложение одной командой ` docker-compose up --build`.



## Рекомендуемый пользовательский сценарий

**Для Swagger-UI или Postman:**
1. Создать счет POST `/api/payment-service/{userId}/account`
2. Пополнить счет  PATCH `/api/payment-service/{id}/top-up/{sum}/account`
3. При желании можно проверить баланс счета POST `/api/payment-service/{id}/balance`
4. Добавить заказ POST `api/order-service/order/{userId}/{amount}/{description}`
    При указании несуществующего айди пользователя заказ добавляется в БД, но считается отмененным.
5. Посмотреть список заказов GET `api/order-service/orders`
6. Посмотреть статус сделанного заказа GET `api/order-service/{id}/status`
7. Можно проверить, что сумма действительно списалась со счета `/api/payment-service/{id}/balance`

**Для фронтенда:**

Все команды в веб-интерфейсе расположены в рекомендуемом порядке для тестирования

Для `Payment-service`
1. Создание счета - `Create account`
2. Пополнение счета (важно делать это именно по айди счета, а не юзера) - `Top up`
3. Проверить баланс счета тоже по айди счета - `Get balance`
4. Обновить список всех счетов - `Reload all accounts`

Для `Order-service`:
1. Создать заказ (важно создавать заказ по айди юзера, а не по айди счета) - `Create order`
2. Получить статус заказа по айди заказа - `Get order status`
3. Обновить список всех заказов - `Reload all orders`

## Технологии:
### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- OpenAPI / Swagger

### Frontend
- React
- Vite
- Material UI (MUI)

### Infrastructure
- PostgreSQL
- RabbitMQ
- Docker 