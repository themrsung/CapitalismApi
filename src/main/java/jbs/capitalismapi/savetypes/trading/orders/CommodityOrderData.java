package jbs.capitalismapi.savetypes.trading.orders;

import jbs.capitalismapi.savetypes.trading.CommodityPortfolioEntryData;
import org.bukkit.Material;

import java.util.Date;

public class CommodityOrderData {
    public CommodityOrderData(CommodityOrderData cod) {
        type = cod.type;
        uuid = cod.uuid;
        senderUuid = cod.senderUuid;
        time = cod.time;
        price = cod.price;
        quantity = cod.quantity;
        feePaid = cod.feePaid;
        feeRate = cod.feeRate;
        moneyCollateral = cod.moneyCollateral;
        materialCollateralData = cod.materialCollateralData;
    }
    public CommodityOrderData() {}
    public CommodityOrderType type = null;
    public String uuid = null;
    public String senderUuid = null;
    public Material material = null;
    public Date time = null;
    public double price = 0d;
    public int quantity = 0;
    public double feePaid = 0d;
    public float feeRate = 0f;
    public double moneyCollateral = 0d;
    public CommodityPortfolioEntryData materialCollateralData = new CommodityPortfolioEntryData();
}
