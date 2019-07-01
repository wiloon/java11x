package com.wiloon.javax.kafka;

import com.wiloon.javax.kafka.proto.UserProto;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProtobufSerializerProducer0 {

    public static final String TOPIC_NAME = "driving_event_data_internal";
    private static Properties props = new Properties();

    static {
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    }

    public static void main(String[] args) {
        Producer<String, byte[]> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            UserProto.User.Builder builder = UserProto.User.newBuilder();
            builder.setId(101L);
            builder.setName("kafka");
            builder.setEmail("serializer@kafka.com"+i);
            builder.setSex(1);

            producer.send(new ProducerRecord<>(TOPIC_NAME, Long.toString(101L), builder.build().toByteArray()));
            System.out.println("sent:" + i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}
