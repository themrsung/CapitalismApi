package jbs.capitalismapi.types.stocks;

import jbs.capitalismapi.savetypes.stocks.StockPortfolioApiData;
import jbs.capitalismapi.savetypes.stocks.StockPortfolioEntryApiData;

import java.util.ArrayList;

public class StockPortfolioApi {
    public StockPortfolioApi(
            ArrayList<StockPortfolioEntryApi> entries
    ) {
        this.entries = entries;
    }
    public StockPortfolioApi() {}

    // Stocks held in this portfolio
    // 해당 포트폴레오 안에 포함된 주식
    ArrayList<StockPortfolioEntryApi> entries = new ArrayList<StockPortfolioEntryApi>();

    public ArrayList<StockPortfolioEntryApi> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<StockPortfolioEntryApi> entries) {
        this.entries = entries;
    }

    public StockPortfolioApiData toData() {
        StockPortfolioApiData data = new StockPortfolioApiData();

        for (StockPortfolioEntryApi entry : entries) {
            data.entryData.add(entry.toData());
        }

        return data;
    }

    public static StockPortfolioApi fromData(StockPortfolioApiData data) {
        StockPortfolioApi portfolio = new StockPortfolioApi();

        for (StockPortfolioEntryApiData entryData : data.entryData) {
            portfolio.getEntries().add(StockPortfolioEntryApi.fromData(entryData));
        }

        return portfolio;
    }
}
