package jbs.capitalismapi.types.trading;

import jbs.capitalismapi.savetypes.trading.CommodityPortfolioData;
import jbs.capitalismapi.savetypes.trading.CommodityPortfolioEntryData;
import org.bukkit.Material;

import java.util.ArrayList;

public class CommodityPortfolio {
    public CommodityPortfolio(CommodityPortfolio cp) {
        entries = cp.entries;
    }
    public CommodityPortfolio() {
        entries = new ArrayList<CommodityPortfolioEntry>();
    }
    ArrayList<CommodityPortfolioEntry> entries;

    public ArrayList<CommodityPortfolioEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<CommodityPortfolioEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(CommodityPortfolioEntry entry) {
        if (entry.getMaterial() == null) return;

        for (CommodityPortfolioEntry e : entries) {
            if (e.getMaterial() == entry.getMaterial()) {
                e.changeQuantity(entry.quantity);
                return;
            }
        }

        entries.add(entry);
    }

    public int getQuantityOf(Material m) {
        int q = 0;

        for (CommodityPortfolioEntry e : entries) {
            if (e.getMaterial() == m) {
                q += e.getQuantity();
            }
        }

        return q;
    }

    public void removeEntry(CommodityPortfolioEntry entry) {
        if (entry.getMaterial() == null) return;

        for (CommodityPortfolioEntry e : entries) {
            if (e.getMaterial() == entry.getMaterial()){
                e.changeQuantity(-entry.getQuantity());
                if (e.getQuantity() < 1) {
                    entries.remove(e);
                    return;
                }
            }
        }
    }

    public int size() {
        int s = 0;

        for (CommodityPortfolioEntry cpe : entries) {
            s += cpe.quantity;
        }

        return s;
    }

    public int getEntrySize() {
        int s = 0;

        for (CommodityPortfolioEntry cpe : entries) {
            if (cpe.getQuantity() > 0) s++;
        }

        return s;
    }

    public CommodityPortfolioData toData() {
        CommodityPortfolioData cpd = new CommodityPortfolioData();

        for (CommodityPortfolioEntry e : entries) {
            cpd.entryData.add(e.toData());
        }

        return cpd;
    }

    public static CommodityPortfolio fromData(CommodityPortfolioData data) {
        CommodityPortfolio cp = new CommodityPortfolio();

        for (CommodityPortfolioEntryData ed : data.entryData) {
            cp.addEntry(CommodityPortfolioEntry.fromData(ed));
        }

        return cp;
    }
}
