package jbs.capitalismapi.types.stocks.orders;

import jbs.capitalismapi.types.entities.EconomicEntity;

public class StockBuyOrder extends StockOrder {
    public StockBuyOrder(
            EconomicEntity sender,
            String symbol,
            double price,
            int quantity,
            float feeRate
    ) {
        super(sender, symbol, price, quantity, feeRate);
        this.moneyCollateral = price * quantity + this.feePaid;
    }
    public StockBuyOrder(StockBuyOrder o) {
        super(o);
    }
    public StockBuyOrder() {
        super();
    }
}
