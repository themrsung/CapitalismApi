package jbs.capitalismapi.types.stocks;

import jbs.capitalismapi.savetypes.stocks.StockPortfolioData;
import jbs.capitalismapi.savetypes.stocks.StockPortfolioEntryData;

import java.util.ArrayList;

public class StockPortfolio {
    public StockPortfolio(ArrayList<StockPortfolioEntry> entries) {
        this.entries = entries;
    }
    public StockPortfolio(StockPortfolioEntry entry) {
        this.entries = new ArrayList<StockPortfolioEntry>();
        entries.add(entry);
    }
    public StockPortfolio(StockPortfolio sp) {
        for (StockPortfolioEntry spe : sp.entries) {
            entries.add(
                    new StockPortfolioEntry(spe)
            );
        }
    }
    public StockPortfolio() {

    }
    ArrayList<StockPortfolioEntry> entries = new ArrayList<StockPortfolioEntry>();
    public ArrayList<StockPortfolioEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<StockPortfolioEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(StockPortfolioEntry entry) {
        if (entry.getQuantity() < 1) return;

        for (StockPortfolioEntry e : entries) {
            if (e.getSymbol().equalsIgnoreCase(entry.getSymbol()))
            {
                double totalCostBefore = e.getPurchasePrice() * e.getQuantity();
                double totalCostDelta = entry.getPurchasePrice() * entry.getQuantity();
                double totalCostAfter = totalCostBefore + totalCostDelta;

                e.setQuantity(e.getQuantity() + entry.getQuantity());
                e.setPurchasePrice(totalCostAfter / e.getQuantity());

                return;
            }
        }

        entries.add(entry);
    }

    public void addEntries(ArrayList<StockPortfolioEntry> entries) {
        for (StockPortfolioEntry e : entries) {
            addEntry(e);
        }
    }

    public void addPortfolio(StockPortfolio portfolio) {
        addEntries(portfolio.entries);
    }

    public void removeEntry(StockPortfolioEntry entry) {
        for (StockPortfolioEntry e : entries) {
            if (e.getSymbol().equalsIgnoreCase(entry.getSymbol())) {
                e.changeQuantity(-entry.getQuantity());
                if (e.getQuantity() <= 0) {
                    entries.remove(e);
                    return;
                }
            }
        }
    }

    public void removeAll(String symbol) {
        entries.removeIf(e -> e.getSymbol().equalsIgnoreCase(symbol));
    }

    public boolean contains(StockPortfolioEntry entry) {
        for (StockPortfolioEntry e : entries) {
            if (e.getSymbol().equalsIgnoreCase(entry.getSymbol())) {
                if (e.getQuantity() >= entry.getQuantity()) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean contains(String symbol) {
        for (StockPortfolioEntry e : entries) {
            if (e.getSymbol().equalsIgnoreCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    public int getQuantityOf(String symbol) {
        for (StockPortfolioEntry e : entries) {
            if (e.getSymbol().equalsIgnoreCase(symbol)) {
                return e.getQuantity();
            }
        }

        return 0;
    }

    public StockPortfolioData toData() {
        ArrayList<StockPortfolioEntryData> entries = new ArrayList<StockPortfolioEntryData>();
        for (StockPortfolioEntry e : this.entries) {
            entries.add(e.toData());
        }

        StockPortfolioData d = new StockPortfolioData();
        d.entryData = entries;
        return d;
    }
    public static StockPortfolio fromData(StockPortfolioData data) {
        ArrayList<StockPortfolioEntry> entries = new ArrayList<StockPortfolioEntry>();

        for (StockPortfolioEntryData sped : data.entryData) {
            entries.add(StockPortfolioEntry.fromData(sped));
        }

        return new StockPortfolio(entries);
    }
}
