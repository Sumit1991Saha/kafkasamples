import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // Need to provide at leaset 1 broker details as producer will fetch other broker details from the metadata that zookepeer would send
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String, String> producerRecord;

        try {
            for (int i = 0; i < 150; ++i ) {
                producerRecord =
                        new ProducerRecord<String, String>(Topics.TOPIC_BOTH_REPLICATION_AND_PARTITION.getTopicName(),
                                Integer.toString(i), "MyMessage: " + i);
                myProducer.send(producerRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myProducer.close();
        }


    }
}
