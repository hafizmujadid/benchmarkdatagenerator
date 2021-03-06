package edu.tu.berlin.dima.benchmark.datagenerator;

public class Bid {
    private long auctionId;
    private double price;
    private long bidderId;
    private long timestamp;

    public Bid(long auctionId, double price, long bidderId, long timestamp) {
        this.auctionId = auctionId;
        this.price = price;
        this.bidderId = bidderId;
        this.timestamp = timestamp;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return auctionId +
                "," + price +
                "," + bidderId +
                "," + timestamp;
    }
}
