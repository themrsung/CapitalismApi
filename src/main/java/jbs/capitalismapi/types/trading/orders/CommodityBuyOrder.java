package jbs.capitalismapi.types.trading.orders;

import jbs.capitalismapi.types.entities.player.CapitalismPlayer;
import org.bukkit.Material;

public class CommodityBuyOrder extends CommodityOrder {
    public CommodityBuyOrder(
            CapitalismPlayer sender,
            Material material,
            double price,
            int quantity,
            float feeRate
    ) {
        super(sender, material, price, quantity, feeRate);
        this.moneyCollateral = price * quantity * this.feePaid;
    }
    public CommodityBuyOrder(CommodityBuyOrder o) {
        super(o);
    }
    public CommodityBuyOrder() {
        super();
    }
}
