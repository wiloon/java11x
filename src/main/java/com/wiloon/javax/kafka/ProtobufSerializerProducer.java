package com.wiloon.javax.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProtobufSerializerProducer {

    public static final String TOPIC_NAME = "topic0";
    private static Properties props = new Properties();

    static {
        props.put("bootstrap.servers", "127.0.0.1");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.wiloon.javax.kafka.ProtobufSerializer");
    }

    public static void main(String[] args) {
        Producer<String, User> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            User user = new User(101L, "kafka", "serializer@kafka.com" + i, 1);
            producer.send(new ProducerRecord<String, User>(TOPIC_NAME, Long.toString(user.getId()), user));
            System.out.println("sent:" + user);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}
