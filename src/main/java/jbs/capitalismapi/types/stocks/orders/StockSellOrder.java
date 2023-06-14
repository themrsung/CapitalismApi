package jbs.capitalismapi.types.stocks.orders;

import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.stocks.StockPortfolioEntry;

public class StockSellOrder extends StockOrder {
    public StockSellOrder(
            EconomicEntity sender,
            String symbol,
            double price,
            int quantity,
            float feeRate
    ) {
        super(sender, symbol, price, quantity, feeRate);
        this.moneyCollateral = 0d;
        this.feePaid = 0d;
        this.stockCollateral = new StockPortfolioEntry(symbol, quantity);
    }
    public StockSellOrder(StockSellOrder o) {
        super(o);
    }
    public StockSellOrder() {
        super();
    }

    @Override
    public void onFulfilled(double price, int quantity) {
        super.onFulfilled(price, quantity);

        // Reduce stock collateral
        stockCollateral.changeQuantity(-quantity);
    }
}
