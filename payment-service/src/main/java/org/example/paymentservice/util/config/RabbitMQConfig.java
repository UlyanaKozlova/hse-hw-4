package org.example.paymentservice.util.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    TopicExchange orderExchange() {
        return new TopicExchange("order.exchange");
    }

    @Bean
    TopicExchange paymentExchange() {
        return new TopicExchange("payment.exchange");
    }
    @Bean
    Queue orderCreatedQueue() {
        return new Queue("order.created.queue", true);
    }

    @Bean
    Binding orderCreatedBinding() {
        return BindingBuilder
                .bind(orderCreatedQueue())
                .to(orderExchange())
                .with("order.created");
    }
}
