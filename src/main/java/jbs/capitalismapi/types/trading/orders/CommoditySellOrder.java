package jbs.capitalismapi.types.trading.orders;

import jbs.capitalismapi.types.entities.player.CapitalismPlayer;
import jbs.capitalismapi.types.trading.CommodityPortfolioEntry;
import org.bukkit.Material;

public class CommoditySellOrder extends CommodityOrder {
    public CommoditySellOrder(
            CapitalismPlayer sender,
            Material material,
            double price,
            int quantity,
            float feeRate
    ) {
        super(sender, material, price, quantity, feeRate);
        this.feePaid = 0d;
        this.moneyCollateral = 0d;
        this.materialCollateral = new CommodityPortfolioEntry(material, quantity);
    }
    public CommoditySellOrder(CommoditySellOrder cso) {
        super(cso);
    }
    public CommoditySellOrder() {
        super();
    }

    @Override
    public void onFulfilled(double price, int quantity) {
        super.onFulfilled(price, quantity);

        // Reduce material collateral
        materialCollateral.changeQuantity(-quantity);
    }
}
