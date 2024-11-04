package com.example.bookmanager.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Value("${rabbit.queue.name}")
    private String queueName;

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "#{@environment.getProperty('rabbit.queue.name')}")
    public void receiveMessage(String message) {
        logger.info("message: {}", message);
    }
}
