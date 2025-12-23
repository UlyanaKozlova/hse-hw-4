package org.example.orderservice.util.config;


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
    Queue paymentResultQueue() {
        return new Queue("payment.result.queue", true);
    }

    @Bean
    Binding paymentResultBinding() {
        return BindingBuilder
                .bind(paymentResultQueue())
                .to(paymentExchange())
                .with("payment.result");
    }
}
