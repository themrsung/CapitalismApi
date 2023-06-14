package jbs.capitalismapi.types.trading;

import jbs.capitalismapi.savetypes.trading.CommodityPortfolioEntryData;
import org.bukkit.Material;

public class CommodityPortfolioEntry {
    public CommodityPortfolioEntry(Material material, int quantity) {
        this.material = material;
        this.quantity = quantity;
    }
    public CommodityPortfolioEntry() {
        this.material = null;
        this.quantity = 0;
    }
    Material material;
    int quantity;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void changeQuantity(int delta) {
        quantity += delta;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CommodityPortfolioEntryData toData() {
        CommodityPortfolioEntryData cpd = new CommodityPortfolioEntryData();

        cpd.material = material;
        cpd.quantity = quantity;

        return cpd;
    }

    public static CommodityPortfolioEntry fromData(CommodityPortfolioEntryData data) {
        return new CommodityPortfolioEntry(
                data.material,
                data.quantity
        );
    }
}
