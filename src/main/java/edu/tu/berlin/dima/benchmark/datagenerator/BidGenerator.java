package edu.tu.berlin.dima.benchmark.datagenerator;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bid type events generator
 * @author mujadid
 */
public class BidGenerator extends Thread{

	RateLimiter limiter; //controls input rate

    AtomicInteger seq; //sequence number for bid events
    Producer<Long,String> bidProducer; //kafka producer


    /**
     * Constructor
     * @param seq
     */
    public BidGenerator(AtomicInteger seq) {
        this.seq = seq;
        limiter = RateLimiter.create(Constants.INPUT_RATE);
        bidProducer = KafkaSender.getPersonProducer("bid");
    }

    /**
     * starts thread to produce events
     */
    @Override
    public void run() {
        while (true){
            if (limiter.tryAcquire()) {
                long id = seq.getAndIncrement();
                Bid bid = new Bid(id, RandomUtils.nextDouble()%300,
                        RandomUtils.nextLong(1,SeqGenerators.person.get()),
                        System.currentTimeMillis());
                ProducerRecord<Long,String> record= new ProducerRecord<Long, String>(Constants.TOPIC_BID,
                        Thread.currentThread().getId(),bid.toString());
                bidProducer.send(record);
                System.out.println(id);
            }
        }
    }


}


