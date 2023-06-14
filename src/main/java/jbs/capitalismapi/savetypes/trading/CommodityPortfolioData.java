package jbs.capitalismapi.savetypes.trading;

import java.util.ArrayList;

public class CommodityPortfolioData {
    public CommodityPortfolioData(CommodityPortfolioData cpd) {
        entryData = cpd.entryData;
    }
    public CommodityPortfolioData() {}
    public ArrayList<CommodityPortfolioEntryData> entryData = new ArrayList<CommodityPortfolioEntryData>();
}
