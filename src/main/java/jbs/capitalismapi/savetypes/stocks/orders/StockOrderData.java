package jbs.capitalismapi.savetypes.stocks.orders;

import jbs.capitalismapi.savetypes.stocks.StockPortfolioEntryData;

import java.util.Date;

public class StockOrderData {
    public StockOrderData(StockOrderData sod) {
        type = sod.type;
        uuid = sod.uuid;
        senderUuid = sod.senderUuid;
        symbol = sod.symbol;
        time = sod.time;
        price = sod.price;
        quantity = sod.quantity;
        feePaid = sod.feePaid;
        feeRate = sod.feeRate;
        moneyCollateral = sod.moneyCollateral;
        stockCollateral = sod.stockCollateral;
    }
    public StockOrderData() {}

    public StockOrderType type = null;
    public String uuid = null;
    public String senderUuid = null;
    public String symbol = null;
    public Date time = null;
    public double price = 0d;
    public int quantity = 0;
    public double feePaid = 0d;
    public float feeRate = 0f;
    public double moneyCollateral = 0d;
    public StockPortfolioEntryData stockCollateral = new StockPortfolioEntryData();
}
