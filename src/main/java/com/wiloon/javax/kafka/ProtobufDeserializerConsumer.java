package com.wiloon.javax.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ProtobufDeserializerConsumer {
    private static Properties props = new Properties();
    public static final String TOPIC_NAME = "producer-0";
    private static boolean isClose = false;

    static {
        props.put("bootstrap.servers", "kafka broker address...");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
    }

    public static void main(String args[]) {
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (!isClose) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, byte[]> record : records)
                System.out.printf("key = %s, value = %s%n", record.key(), new User(record.value()));
        }

        consumer.close();
    }
}
