package ru.nedorezova.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nedorezova.orderservice.entity.Order;
import ru.nedorezova.orderservice.kafka.OrderProducer;
import ru.nedorezova.orderservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public void save(Order order) {
        orderRepository.save(order);
        orderProducer.sendMessage(order);
    }
}
