package com.wiloon.javax.kafka;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class ProtobufSerializer implements Serializer<Protobufable> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Protobufable data) {
        return data.encode();
    }

    @Override
    public void close() {
    }
}
