package ru.nedorezova.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.stereotype.Service;
import ru.nedorezova.notificationservice.entity.Order;

import java.util.Properties;

@Service
public class KafkaOrderProcessor {


    private final ObjectMapper objectMapper = new ObjectMapper();

    public void processOrders() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> orderStream = builder.stream("order-topic");

        KTable<String, Double> orderTotals = orderStream
                .mapValues(orderJson -> {
                    try {
                        Order order = objectMapper.readValue(orderJson, Order.class);
                        return order.getProducts().stream()
                                .mapToDouble(p -> p.getPrice())
                                .sum();
                    } catch (JsonProcessingException e) {
                        System.err.println("Error parsing JSON: " + e.getMessage());
                        return 0.0;
                    }
                })
                .groupBy((key, total) -> key)
                .reduce((agg, total) -> total);

        orderTotals.toStream().to("order-totals");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        System.out.println(orderTotals);
    }
}
