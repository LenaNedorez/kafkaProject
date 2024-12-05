package ru.nedorezova.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.nedorezova.notificationservice.entity.Order;

//@Service
public class KafkaOrderListener {

//    private final ObjectMapper objectMapper;
//
//    public KafkaOrderListener(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @KafkaListener(topics = "order-topic", containerFactory = "OrderKafkaContainerFactory")
//    public void sendConfirmationOrderToEmail(String orderEvent) throws JsonProcessingException {
//
//        Order order = objectMapper.readValue(orderEvent, Order.class);;
//
//        System.out.println(order);
//    }
}
