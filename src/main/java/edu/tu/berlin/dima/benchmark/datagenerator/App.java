package edu.tu.berlin.dima.benchmark.datagenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static final int NUM_PERSON_GENERATORS =5;
    private static final int NUM_AUCTION_GENERATORS =5;
    private static final int NUM_BID_GENERATORS =5;

    public static void main(String[] args){
        Thread [] personGenerators = new PersonGenerator[NUM_PERSON_GENERATORS];
		Thread [] auctionGenerators = new AuctionGenerator[NUM_AUCTION_GENERATORS];
		//Thread [] bidGenerators = new BidGenerator[NUM_BID_GENERATORS];

        AtomicInteger person = SeqGenerators.person;
        AtomicInteger auction = SeqGenerators.auction;
        //AtomicInteger bid = SeqGenerators.bid;
        //AtomicInteger items = SeqGenerators.items;
        //AtomicInteger categories = SeqGenerators.items;

        /*for(int i=1;i<10000;i++){
            items.incrementAndGet();
        }


        for(int i=1;i<10000;i++){
            categories.incrementAndGet();
        }*/

        for(int i=0;i<NUM_PERSON_GENERATORS;i++){
            personGenerators[i]= new PersonGenerator(person);
            personGenerators[i].start();
        }
		
		for(int i=0;i<NUM_AUCTION_GENERATORS;i++){
            auctionGenerators[i]= new AuctionGenerator(auction);
            auctionGenerators[i].start();
        }
		
		/*for(int i=0;i<NUM_BID_GENERATORS;i++){
            bidGenerators[i]= new BidGenerator(bid);
            bidGenerators[i].start();
        }*/



    }
}
