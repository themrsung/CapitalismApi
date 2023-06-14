package jbs.capitalismapi.types.stocks;

import jbs.capitalismapi.savetypes.stocks.StockPortfolioEntryApiData;

import java.util.Date;

public class StockPortfolioEntryApi {
    public StockPortfolioEntryApi(
            String symbol,
            int quantity,
            double price,
            double purchasePrice,
            Date purchaseDate
    ) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }
    public StockPortfolioEntryApi() {}

    // Symbol of this stock
    // 종목코드
    String symbol = null;

    // Quantity of shares
    // 주식수
    int quantity = 0;

    // Stock price
    // 주가
    double price = 0d;

    // Purchase price of this stock
    // 매입가
    double purchasePrice = 0d;

    // Purchase date of this stock
    // 매입일
    Date purchaseDate = null;

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public StockPortfolioEntryApiData toData() {
        StockPortfolioEntryApiData data = new StockPortfolioEntryApiData();

        data.symbol = symbol;
        data.quantity = quantity;
        data.price = price;
        data.purchasePrice = purchasePrice;
        data.purchaseDate = purchaseDate;

        return data;
    }

    public static StockPortfolioEntryApi fromData(StockPortfolioEntryApiData data) {
        return new StockPortfolioEntryApi(
                data.symbol,
                data.quantity,
                data.price,
                data.purchasePrice,
                data.purchaseDate
        );
    }
}
