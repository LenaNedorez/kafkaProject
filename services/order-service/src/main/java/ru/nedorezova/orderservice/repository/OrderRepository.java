package ru.nedorezova.orderservice.repository;

import org.springframework.stereotype.Repository;
import ru.nedorezova.orderservice.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }
}