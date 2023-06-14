package jbs.capitalismapi.types.entities.companies;

import jbs.capitalismapi.savetypes.entities.EconomicEntityType;
import jbs.capitalismapi.savetypes.entities.companies.SecuritiesExchangeData;
import jbs.capitalismapi.types.stocks.StockListing;
import jbs.capitalismapi.types.stocks.StockPortfolio;
import jbs.capitalismapi.types.stocks.orders.StockOrder;

import java.util.ArrayList;
import java.util.UUID;

public class SecuritiesExchange extends Company {
    public SecuritiesExchange(
            UUID uuid,
            String name,
            double balance,
            StockPortfolio stockPortfolio,
            String symbol,
            int shareCount
    ) {
        super(uuid, name, balance, stockPortfolio, symbol, shareCount);
    }
    public SecuritiesExchange(SecuritiesExchange se) {
        super(se);
        stockListings = se.stockListings;
        buyerFeeRate = se.buyerFeeRate;
        sellerFeeRate = se.sellerFeeRate;
    }
    public SecuritiesExchange(UUID uuid) {
        super(uuid);
    }
    public SecuritiesExchange() {
        super();
    }

    ArrayList<StockListing> stockListings = new ArrayList<StockListing>();

    public ArrayList<StockListing> getStockListings() {
        return stockListings;
    }
    public void addStockListing(StockListing stockListing) {
        this.stockListings.add(stockListing);
        stockListing.setExchange(this);
    }

    public void setStockListings(ArrayList<StockListing> stockListings) {
        this.stockListings = stockListings;
    }

    public void removeStockListing(StockListing stockListing) {
        ArrayList<StockOrder> orders = new ArrayList<StockOrder>(stockListing.getOrders());
        for (StockOrder o : orders) {
            stockListing.cancelOrder(o);
        }

        this.stockListings.remove(stockListing);
    }

    float buyerFeeRate = 0f;
    float sellerFeeRate = 0f;

    public float getBuyerFeeRate() {
        return buyerFeeRate;
    }

    public void setBuyerFeeRate(float buyerFeeRate) {
        this.buyerFeeRate = buyerFeeRate;
    }

    public float getSellerFeeRate() {
        return sellerFeeRate;
    }

    public void setSellerFeeRate(float sellerFeeRate) {
        this.sellerFeeRate = sellerFeeRate;
    }

    @Override
    public SecuritiesExchangeData toData() {
        SecuritiesExchangeData sed = new SecuritiesExchangeData(super.toData());
        sed.type = EconomicEntityType.SECURITIES_EXCHANGE;

        for (StockListing sl : stockListings) {
            sed.stockListingData.add(sl.toData());
        }
        sed.buyerFeeRate = buyerFeeRate;
        sed.sellerFeeRate = sellerFeeRate;

        return sed;
    }

    public static SecuritiesExchange fromData(SecuritiesExchangeData data) {
        return new SecuritiesExchange(UUID.fromString(data.uuid));
    }
}
