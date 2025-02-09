package ru.nedorezova.orderservice.mapper;

import org.mapstruct.Mapper;
import ru.nedorezova.orderservice.dto.OrderDto;
import ru.nedorezova.orderservice.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order order);
}
