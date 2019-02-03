package edu.tu.berlin.dima.benchmark.datagenerator;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonGenerator extends Thread {
    RateLimiter limiter;

    AtomicInteger seq;
    Producer<Long,String> personProducer;

    public PersonGenerator(AtomicInteger seq) {
        this.seq = seq;
        limiter = RateLimiter.create(Constants.INPUT_RATE);
        personProducer = KafkaSender.getPersonProducer("person");
    }

    @Override
    public void run() {
        while (seq.get()<Constants.RECORD_COUNT){
            if (limiter.tryAcquire()) {
                int id = seq.getAndIncrement();
                Person person = new Person(id, RandomStringUtils.randomAlphabetic(20),
                        RandomStringUtils.randomAlphabetic(20)+"@gmail.com",
                        RandomStringUtils.randomAlphabetic(12),
                        RandomStringUtils.randomAlphabetic(20),
                        RandomStringUtils.randomAlphabetic(20),
                        System.currentTimeMillis());
                ProducerRecord<Long,String> record= new ProducerRecord<Long, String>(Constants.TOPIC_PERSON,
                        Thread.currentThread().getId(),person.toString());
                personProducer.send(record);
                System.out.println(id);
            }
        }
        System.out.println("exit");
    }
}
