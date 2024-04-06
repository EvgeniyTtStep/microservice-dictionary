package com.example.microservicedictionary.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
    private RabbitTemplate rabbitTemplate;
    private String translationExchange;
    private String translationCompletedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${translation.exchange}") final String translationExchange,
                    @Value("${translation.completed.key}") final String translationCompletedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.translationExchange = translationExchange;
        this.translationCompletedRoutingKey = translationCompletedRoutingKey;
    }

    public void send(final TranslationCompletedEvent translationCompletedEvent) {
        rabbitTemplate.convertAndSend(
                translationExchange,
                translationCompletedRoutingKey,
                translationCompletedEvent);
    }

}
