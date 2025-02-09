package ru.nedorezova.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nedorezova.orderservice.dto.OrderDto;
import ru.nedorezova.orderservice.entity.Order;
import ru.nedorezova.orderservice.mapper.OrderMapper;
import ru.nedorezova.orderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/create-order")
    public void save(@RequestBody Order order) {
        orderService.save(order);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders(){
        List<OrderDto> orderDtos = orderService.getOrders()
                .stream()
                .map(orderMapper::toDto)
                .toList();
        return ResponseEntity.ok(orderDtos);
    }
}