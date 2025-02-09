package ru.nedorezova.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "order")
public class Order {

    private int id;
    private String name;
    private List<Product> products;

}
