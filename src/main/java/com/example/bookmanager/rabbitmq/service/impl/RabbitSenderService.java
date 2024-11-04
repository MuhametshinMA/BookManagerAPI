package com.example.bookmanager.rabbitmq.service.impl;

import com.example.bookmanager.rabbitmq.service.SenderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitSenderService implements SenderService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String message) {
        rabbitTemplate.convertAndSend("bookQueue", message);
    }
}
