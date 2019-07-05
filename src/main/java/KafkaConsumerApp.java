import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class KafkaConsumerApp {

    private static final String REPLICATED_TOPIC = "my_replicated_topic";

    public static void main(String[] args) {

        Properties properties = new Properties();
        // Need to provide at leaset 1 broker details as producer will fetch other broker details from the metadata that zookepeer would send
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> myProducer = new KafkaConsumer<String, String>(properties);




    }
}
