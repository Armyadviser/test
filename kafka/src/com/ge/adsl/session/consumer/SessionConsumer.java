package com.ge.adsl.session.consumer;

import com.ge.adsl.session.serde.DialupDeserializer;
import com.ge.util.log.Log;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by Storm_Falcon on 2016/9/9.
 *
 */
public class SessionConsumer {

    private final static String TOPIC = "session-dialup";

    private KafkaConsumer<String, String> consumer;

    private Log log;

    public SessionConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "202.96.74.21:9092,202.96.74.23:9092,202.96.74.24:9092");
        properties.put("group.id", "test-group");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", DialupDeserializer.class.getName());

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(TOPIC));

        log = Log.getLog("", 10, ".log");
        log.setShowSystemOut(false);
        log.setShowT(false);
    }

    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.forEach(record -> log.toLog(record.value()));
        }
    }

    public static void main(String[] args) {
        new SessionConsumer().run();
    }
}
