package ru.nedorezova.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.nedorezova.orderservice.entity.Order;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Order> template;

    public void sendMessage(Order order) {
        template.send("order-topic", order);
    }
}
