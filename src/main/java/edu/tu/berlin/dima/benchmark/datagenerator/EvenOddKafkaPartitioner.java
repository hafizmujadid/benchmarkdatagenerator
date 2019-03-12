package edu.tu.berlin.dima.benchmark.datagenerator;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import java.util.Map;

public class EvenOddKafkaPartitioner implements Partitioner {

    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return ((Long) o).intValue() % 2;
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
