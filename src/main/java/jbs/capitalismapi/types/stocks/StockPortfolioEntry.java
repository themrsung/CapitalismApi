package jbs.capitalismapi.types.stocks;

import jbs.capitalismapi.savetypes.stocks.StockPortfolioEntryData;

import java.util.Date;

public class StockPortfolioEntry {
    public StockPortfolioEntry(String symbol, int quantity, double purchasePrice, Date purchaseDate) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }
    public StockPortfolioEntry(String symbol, int quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }
    public StockPortfolioEntry(String symbol) {
        this.symbol = symbol;
        this.quantity = 1;
    }
    public StockPortfolioEntry(StockPortfolioEntry spe) {
        symbol = spe.symbol;
        quantity = spe.quantity;
    }
    public StockPortfolioEntry() {

    }
    String symbol = null;
    int quantity = 0;
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void changeQuantity(int delta) {
        this.quantity += delta;
    }

    double purchasePrice = 0d;
    Date purchaseDate = new Date();

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public StockPortfolioEntryData toData() {
        StockPortfolioEntryData d = new StockPortfolioEntryData();
        d.symbol = symbol;
        d.quantity = quantity;
        d.purchasePrice = purchasePrice;
        d.purchaseDate = purchaseDate;
        return d;
    }

    public static StockPortfolioEntry fromData(StockPortfolioEntryData d) {
        return new StockPortfolioEntry(
                d.symbol,
                d.quantity,
                d.purchasePrice,
                d.purchaseDate
        );
    }
}
