package jbs.capitalismapi.types.stocks.orders;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.stocks.orders.StockOrderData;
import jbs.capitalismapi.savetypes.stocks.orders.StockOrderType;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.stocks.StockPortfolioEntry;

import java.util.Date;
import java.util.UUID;

public class StockOrder {
    public StockOrder(
            EconomicEntity sender,
            String symbol,
            double price,
            int quantity,
            float feeRate
    ) {
        this.uuid = UUID.randomUUID();
        this.sender = sender;
        this.symbol = symbol;
        this.time = new Date();
        this.price  = price;
        this.quantity = quantity;
        this.feePaid = price * quantity * feeRate;
        this.feeRate = feeRate;
    }
    public StockOrder(
            UUID uuid,
            EconomicEntity sender,
            String symbol,
            Date time,
            double price,
            int quantity,
            double feePaid,
            float feeRate,
            double moneyCollateral,
            StockPortfolioEntry stockCollateral
    ) {
        this.uuid = uuid;
        this.sender = sender;
        this.symbol = symbol;
        this.time = time;
        this.price = price;
        this.quantity = quantity;
        this.feePaid = feePaid;
        this.feeRate = feeRate;
        this.moneyCollateral = moneyCollateral;
        this.stockCollateral = stockCollateral;
    }
    public StockOrder(StockOrder so) {
        uuid = so.uuid;
        sender = so.sender;
        symbol = so.symbol;
        time = so.time;
        price = so.price;
        quantity = so.quantity;
        feePaid = so.feePaid;
        feeRate = so.feeRate;
        moneyCollateral = so.moneyCollateral;
        stockCollateral = so.stockCollateral;
    }
    public StockOrder() {
        uuid = null;
        sender = null;
        symbol = null;
        time = null;
        price = 0d;
        quantity = 0;
        feePaid = 0d;
        moneyCollateral = 0d;
        stockCollateral = new StockPortfolioEntry();
    }
    UUID uuid;
    EconomicEntity sender;
    String symbol;
    Date time;
    double price;
    int quantity;
    double feePaid;
    float feeRate;
    double moneyCollateral;
    StockPortfolioEntry stockCollateral;

    public void onCancelled() {
        sender.changeBalance(moneyCollateral);
        sender.getStockPortfolio().addEntry(stockCollateral);
    }

    public void onFulfilled(double price, int quantity) {
        float fulfillmentRatio = (float) quantity / this.quantity;
        double actualFee = price * quantity * feeRate;
        double feeRefund = feePaid * fulfillmentRatio - actualFee;

        // Refund overpaid fees
        sender.changeBalance(actualFee);
        this.feePaid -= feeRefund;
        this.moneyCollateral -= feeRefund;

        // Reduce quantity
        this.quantity -= quantity;
    }


    public double getVolume() { return price * quantity; }

    public UUID getUuid() {
        return uuid;
    }

    public EconomicEntity getSender() {
        return sender;
    }

    public String getSymbol() {
        return symbol;
    }

    public Date getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFeePaid() {
        return feePaid;
    }

    public float getFeeRate() {
        return feeRate;
    }

    public double getMoneyCollateral() {
        return moneyCollateral;
    }

    public StockPortfolioEntry getStockCollateral() {
        return stockCollateral;
    }

    public boolean equals(StockOrder so) {
        return this.uuid.equals(so.uuid);
    }

    public StockOrderData toData() {
        StockOrderData sod = new StockOrderData();

        sod.type = StockOrderType.ORDER;
        sod.uuid = uuid.toString();
        sod.senderUuid = sender.getUuid().toString();
        sod.symbol = symbol;
        sod.time = time;
        sod.price = price;
        sod.quantity = quantity;
        sod.feePaid = feePaid;
        sod.feeRate = feeRate;
        sod.moneyCollateral = moneyCollateral;
        sod.stockCollateral = stockCollateral == null ? null : stockCollateral.toData();

        return sod;
    }

    public static StockOrder fromData(StockOrderData data, Capitalism plugin) {
        StockOrder so = new StockOrder(
                UUID.fromString(data.uuid),
                plugin.getEntity(UUID.fromString(data.senderUuid)),
                data.symbol,
                data.time,
                data.price,
                data.quantity,
                data.feePaid,
                data.feeRate,
                data.moneyCollateral,
                data.stockCollateral == null ? new StockPortfolioEntry() : StockPortfolioEntry.fromData(data.stockCollateral)
        );

        return so;
    }
}
