package ru.nedorezova.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.nedorezova.notificationservice.entity.Order;
import ru.nedorezova.notificationservice.entity.Product;

@Service
public class OrderStreamsService {

    private final StreamsBuilder streamsBuilder;
    private final StreamsConfig kafkaStreamsConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderStreamsService(StreamsBuilder streamsBuilder, StreamsConfig kafkaStreamsConfig) {
        this.streamsBuilder = streamsBuilder;
        this.kafkaStreamsConfig = kafkaStreamsConfig;
    }

    @Bean
    public void processOrderStream() {
        KStream<String, String> orderStream = streamsBuilder.stream("order-topic");

        orderStream.foreach((key, orderJson) -> {
            try {
                Order order = objectMapper.readValue(orderJson, Order.class);
                System.out.println("Received order: " + order);
            } catch (JsonProcessingException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
            }
        });

        KTable<String, Double> orderTotals = orderStream
                .mapValues(orderJson -> {
                    try {
                        Order order = objectMapper.readValue(orderJson, Order.class);
                        return order.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum();
                    } catch (JsonProcessingException e) {
                        System.err.println("Error parsing JSON: " + e.getMessage());
                        return 0.0;
                    }
                })
                .groupBy((key, total) -> key)
                .reduce((agg, total) -> total);

        orderTotals.toStream().to("order-totals");

        try (KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), kafkaStreamsConfig)) {
            streams.start();
        }
    }

}
