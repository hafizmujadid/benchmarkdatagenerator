package edu.tu.berlin.dima.benchmark.datagenerator;

public class Auction {
    private long timestamp;
    private long auctionId;
    private long itemId;
    private long sellerId;
    private double initialPrice;
    private int categoryId;
    private long expireDate;

    public Auction(long timestamp, long auctionId, long itemId, long sellerId, double initialPrice, int categoryId, long expireDate) {
        this.timestamp = timestamp;
        this.auctionId = auctionId;
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.initialPrice = initialPrice;
        this.categoryId = categoryId;
        this.expireDate = expireDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return timestamp +
                "," + auctionId +
                "," + itemId +
                "," + sellerId +
                "," + initialPrice +
                "," + categoryId +
                "," + expireDate;
    }
}
