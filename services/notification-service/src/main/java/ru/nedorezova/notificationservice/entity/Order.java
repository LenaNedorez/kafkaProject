package ru.nedorezova.notificationservice.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Order {

    private String name;
    private List<Product> products;

}
