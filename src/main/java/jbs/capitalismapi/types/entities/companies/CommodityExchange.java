package jbs.capitalismapi.types.entities.companies;

import jbs.capitalismapi.savetypes.entities.EconomicEntityType;
import jbs.capitalismapi.savetypes.entities.companies.CommodityExchangeData;
import jbs.capitalismapi.types.stocks.StockPortfolio;
import jbs.capitalismapi.types.trading.CommodityPortfolioEntry;
import jbs.capitalismapi.types.trading.orders.CommodityBuyOrder;
import jbs.capitalismapi.types.trading.orders.CommodityOrder;
import jbs.capitalismapi.types.trading.orders.CommoditySellOrder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class CommodityExchange extends Company {
    public CommodityExchange(
            UUID uuid,
            String name,
            double balance,
            StockPortfolio stockPortfolio,
            String symbol,
            int shareCount
    ) {
        super(uuid, name, balance, stockPortfolio, symbol, shareCount);
    }
    public CommodityExchange(CommodityExchange ce) {
        super(ce);
        orders = ce.orders;
        buyerFeeRate = ce.buyerFeeRate;
        sellerFeeRate = ce.sellerFeeRate;
    }
    public CommodityExchange(UUID uuid) {
        super(uuid);
    }
    public CommodityExchange() {
        super();
    }

    ArrayList<CommodityOrder> orders = new ArrayList<CommodityOrder>();

    public ArrayList<CommodityBuyOrder> getBuyOrders() {
        ArrayList<CommodityBuyOrder> orders = new ArrayList<CommodityBuyOrder>();

        for (CommodityOrder o : getOrders()) {
            if (o instanceof CommodityBuyOrder) orders.add((CommodityBuyOrder) o);
        }

        return orders;
    }

    public ArrayList<CommoditySellOrder> getSellOrders() {
        ArrayList<CommoditySellOrder> orders = new ArrayList<CommoditySellOrder>();

        for (CommodityOrder o : getOrders()) {
            if (o instanceof  CommoditySellOrder) orders.add((CommoditySellOrder) o);
        }

        return orders;
    }

    public void processOrders() {
        ArrayList<CommodityBuyOrder> buyOrders = getBuyOrders();
        ArrayList<CommoditySellOrder> sellOrders = getSellOrders();

        buyOrders.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        sellOrders.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));

        for (CommodityBuyOrder bo : buyOrders) {
            for (CommoditySellOrder so : sellOrders) {
                if (so.getQuantity() > 0) {
                    if (bo.getPrice() >= so.getPrice()) {
                        double price = bo.getTime().before(so.getTime()) ? bo.getPrice() : so.getPrice();
                        int quantity = Math.min(bo.getQuantity(), so.getQuantity());

                        bo.onFulfilled(price, quantity);
                        so.onFulfilled(price, quantity);

                        bo.getSender().getUnreceivedCommodityPortfolio().addEntry(
                                new CommodityPortfolioEntry(bo.getMaterial(), quantity)
                        );

                        double sellSideFee = price * quantity * so.getFeeRate();
                        so.getSender().changeBalance(-sellSideFee);
                        this.changeBalance(sellSideFee);
                    }
                }
            }
        }

        ArrayList<Integer> indicesToClean = new ArrayList<Integer>();
        for (CommodityOrder o : orders) {
            if (o.getQuantity() <= 0) {
                indicesToClean.add(orders.indexOf(o));
            }
        }

        indicesToClean.sort(Comparator.reverseOrder());

        for (int i : indicesToClean) {
            orders.remove(i);
        }
    }

    public ArrayList<CommodityOrder> getOrders() {
        return orders;
    }

    public void addOrder(CommodityOrder order) {
        orders.add(order);
        this.changeBalance(order.getFeePaid());
    }

    public void cancelOrder(CommodityOrder order) {
        order.onCancelled();
        removeOrder(order);
    }

    public void removeOrder(CommodityOrder order) {
        orders.remove(order);
    }

    public void setOrders(ArrayList<CommodityOrder> orders) {
        this.orders = orders;
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

    public CommodityExchangeData toData() {
        CommodityExchangeData ced = new CommodityExchangeData(super.toData());
        ced.type = EconomicEntityType.COMMODITY_EXCHANGE;

        ced.buyerFeeRate = buyerFeeRate;
        ced.sellerFeeRate = sellerFeeRate;

        for (CommodityOrder co : orders) {
            ced.orderData.add(co.toData());
        }

        return ced;
    }

    public static CommodityExchange fromData(CommodityExchangeData data) {
        return new CommodityExchange(UUID.fromString(data.uuid));
    }
}
