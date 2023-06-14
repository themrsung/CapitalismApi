package jbs.capitalismapi.savetypes.trading;

import org.bukkit.Material;

public class CommodityPortfolioEntryData {
    public CommodityPortfolioEntryData(CommodityPortfolioEntryData cpd) {
        material = cpd.material;
        quantity = cpd.quantity;
    }
    public CommodityPortfolioEntryData() {}
    public Material material = null;
    public int quantity = 0;
}
