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
        //while (seq.get()<Constants.PERSON_RECORD_COUNT){
        while (true){
            if (limiter.tryAcquire()) {
                long id = seq.getAndIncrement();
                //200 bytes
                Person person = new Person(id, RandomStringUtils.randomAlphabetic(58),
                        RandomStringUtils.randomAlphabetic(40)+"@gmail.com",
                        RandomStringUtils.randomAlphabetic(20),
                        RandomStringUtils.randomAlphabetic(30),
                        RandomStringUtils.randomAlphabetic(40),
                        System.currentTimeMillis());
                ProducerRecord<Long,String> record= new ProducerRecord<Long, String>(Constants.TOPIC_PERSON,
                        Thread.currentThread().getId(),person.toString());
                personProducer.send(record);
                System.out.println(id);
            }
        }
    }
}
