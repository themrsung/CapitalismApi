package jbs.capitalismapi.savetypes.stocks;

import jbs.capitalismapi.savetypes.stocks.orders.StockOrderData;

import java.util.ArrayList;

public class StockListingData {
    public StockListingData() {}

    public String symbol;
    public double lastTradedPrice = 0d;
    public int lastTradedVolume = 0;
    public ArrayList<StockOrderData> orderData = new ArrayList<StockOrderData>();
}
