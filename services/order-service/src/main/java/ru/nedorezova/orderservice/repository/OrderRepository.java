package ru.nedorezova.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nedorezova.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}