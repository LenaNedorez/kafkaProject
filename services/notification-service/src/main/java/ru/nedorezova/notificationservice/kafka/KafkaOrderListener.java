package ru.nedorezova.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.nedorezova.notificationservice.entity.Order;

@Service
public class KafkaOrderListener {

    @KafkaListener(topics = "order-topic")
    public void sendConfirmationOrderToEmail(Order order) {
        System.out.println(order);
    }
}
