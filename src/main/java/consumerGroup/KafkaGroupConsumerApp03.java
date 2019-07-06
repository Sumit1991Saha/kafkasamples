package consumerGroup;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class KafkaGroupConsumerApp03 {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // Need to provide at leaset 1 broker details as producer will fetch other broker details from the metadata that zookepeer would send
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        Collection<String> topics = new ArrayList<String>() {{
            add("my_big_topic");
        }};
        consumer.subscribe(topics); //This step is not incremental so another such call will override the previous subscribe operation.

        try {
            int count = 0;
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(10); // here timeout is in milli seconds.
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println( count + ". " + String.format("Topic: %s, partition: %d, offset: %d, key: %s value: %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value().toUpperCase()));
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
