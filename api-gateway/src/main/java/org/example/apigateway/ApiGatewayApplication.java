package org.example.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {
    @Value("${order.service.base.url}")
    String orderServiceBaseURL;
    @Value("${payment.service.base.url}")
    String paymentServiceBaseURL;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order_service", r -> r
                        .path("/api/order-service/**")
                        .uri(orderServiceBaseURL))
                .route("payment_service", r -> r
                        .path("/api/payment-service/**")
                        .uri(paymentServiceBaseURL))
                .build();
    }
}
