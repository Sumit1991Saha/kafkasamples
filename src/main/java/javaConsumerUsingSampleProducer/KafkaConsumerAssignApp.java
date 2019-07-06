package javaConsumerUsingSampleProducer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class KafkaConsumerAssignApp {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // Need to provide at leaset 1 broker details as producer will fetch other broker details from the metadata that zookepeer would send
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        Collection<TopicPartition> partitions = new ArrayList<TopicPartition>() {{
            add(new TopicPartition("my_topic1", 2));
            add(new TopicPartition("my_topic2", 0));
        }};

        consumer.assign(partitions);

        try {

            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(10); // here timeout is in milli seconds.
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println( String.format("Topic: %s, partition: %d, offset: %d, key: %s value: %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
