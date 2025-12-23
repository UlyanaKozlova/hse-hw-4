# HSE-HW-3: Online Store
## Асинхронное межсервисное взаимодействие


## Начало работы
``` bash
docker-compose build
docker-compose up
```
## Завершение работы
``` bash
docker-compose down
```
# Документация:
**Оба микросервиса:**

http://localhost:8083/swagger-ui.html

**Отдельные json для каждого микросервиса:**

http://localhost:8083/api/order-service/v3/api-docs

http://localhost:8083/api/payment-service/v3/api-docs

Отображается документация для `order-service` и `payment-service` через `api-gateway`.


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
3. **API Gateway** - `8083`- центральный сервис-посредник, принимающий запросы от клиентов и маршрутизирующий их к соответствующим микросервисам. 


## Docker
Все микросервисы и БД упакованы в Docker-контейнеры, что позволяет развернуть приложение одной командой `docker-compose up`.


## Пользовательские сценарии
1. 
