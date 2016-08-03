package storm_falcon.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

/**
 * Created by Storm_Falcon on 2016/7/29.
 * a test kafka producer.
 */
public class TestProducer {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "202.96.74.21:9092,202.96.74.23:9092,202.96.74.24:9092");
        properties.put("acks", "all");
        properties.put("retries", "0");
        properties.put("batch.size", "16384");//16K
        properties.put("linger.ms", "1");
        properties.put("buffer.memory", "33554432");//32M
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("test", "key" + i, "value" + i + " " + new Date()));
        }
        producer.close();
    }
}
