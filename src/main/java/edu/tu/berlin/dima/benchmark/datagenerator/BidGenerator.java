package edu.tu.berlin.dima.benchmark.datagenerator;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.atomic.AtomicInteger;

public class BidGenerator extends Thread{

	RateLimiter limiter;

    AtomicInteger seq;
    Producer<Long,String> bidProducer;

    public BidGenerator(AtomicInteger seq) {
        this.seq = seq;
        limiter = RateLimiter.create(Constants.INPUT_RATE);
        bidProducer = KafkaSender.getPersonProducer("bid");
    }

    @Override
    public void run() {
        while (seq.get()<Constants.RECORD_COUNT){
            if (limiter.tryAcquire()) {
                int id = seq.getAndIncrement();
                Bid bid = new Bid(id, RandomUtils.nextDouble()%300,
                        RandomUtils.nextInt(1,SeqGenerators.person.get()),
                        System.currentTimeMillis());
                ProducerRecord<Long,String> record= new ProducerRecord<Long, String>(Constants.TOPIC_BID,
                        Thread.currentThread().getId(),bid.toString());
                bidProducer.send(record);
                System.out.println(id);
            }
        }
        System.out.println("exit");
    }


}


