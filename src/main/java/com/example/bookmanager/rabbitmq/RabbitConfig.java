package com.example.bookmanager.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.queue.name}")
    private String queueName;

    @Bean
    public Queue bookQueue() {
        return new Queue(queueName, false);
    }

}
