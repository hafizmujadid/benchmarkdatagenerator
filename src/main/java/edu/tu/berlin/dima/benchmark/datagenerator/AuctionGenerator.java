package edu.tu.berlin.dima.benchmark.datagenerator;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.atomic.AtomicInteger;

public class AuctionGenerator extends Thread {

	RateLimiter limiter;
    Producer<Long,String> auctionProducer;
    AtomicInteger seq;

    AuctionGenerator(AtomicInteger seq) {
        this.seq = seq;
        limiter = RateLimiter.create(Constants.INPUT_RATE);
        auctionProducer = KafkaSender.getPersonProducer("auction");
    }

    @Override
    public void run() {
        while (seq.get()<Constants.RECORD_COUNT){
            if (limiter.tryAcquire()) {
                int id = seq.getAndIncrement();
                long start_time = System.currentTimeMillis();
                long expire_time = start_time + RandomUtils.nextInt(1,60000);
                //40 bytes
                Auction auction = new Auction(start_time,id,RandomUtils.nextInt(1,100000),
                        RandomUtils.nextInt(1,SeqGenerators.person.get()), //person id
                        RandomUtils.nextDouble(10,6000), //price b/w 10 and 6000
                        RandomUtils.nextInt(1,20),expire_time); //categories 1,20
                ProducerRecord<Long,String> record= new ProducerRecord<Long, String>(Constants.TOPIC_AUCTION,
                        Thread.currentThread().getId(),auction.toString());
                auctionProducer.send(record);

                System.out.println(id);
            }
        }
        System.out.println("exit");
    }

}
