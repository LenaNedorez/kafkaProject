package ru.nedorezova.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nedorezova.orderservice.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository extends JpaRepository<> {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }

    public List<Order> findAllOrders() {
        return orders;
    }
}