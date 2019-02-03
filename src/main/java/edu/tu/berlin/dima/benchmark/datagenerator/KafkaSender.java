package edu.tu.berlin.dima.benchmark.datagenerator;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaSender {

    static Producer<Long, String> personProducer;
    static Producer<Long, String> auctionProducer;
    static Producer<Long, String> bidProducer;

    private static Producer<Long, String> createProducer(String clientId) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                Constants.BOOTSTRAP_SERVERS);
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, ParallelismPartitioner.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put("batch.size", 10000);
        props.put("linger.ms", 10);
        return new KafkaProducer<Long, String>(props);
    }

    public static Producer<Long, String> getPersonProducer(String type) {
        if (type.equals("person")) {
            if (personProducer == null)
                personProducer = createProducer("person");
            return personProducer;
        } else if (type.equals("auction")) {
            if (auctionProducer == null)
                auctionProducer = createProducer("auction");
            return auctionProducer;
        } else {
            if (bidProducer == null)
                bidProducer = createProducer("auction");
            return bidProducer;
        }
    }
}
