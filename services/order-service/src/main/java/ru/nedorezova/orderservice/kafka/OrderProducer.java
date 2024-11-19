package ru.nedorezova.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.nedorezova.orderservice.entity.Order;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Order> template;

    public void sendMessage(Order order) {
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(TOPIC, "order-topic")
                .build();
    }
}
