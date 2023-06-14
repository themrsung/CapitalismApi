package jbs.capitalismapi.types.stocks;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.stocks.StockListingData;
import jbs.capitalismapi.savetypes.stocks.orders.StockOrderData;
import jbs.capitalismapi.types.entities.companies.SecuritiesExchange;
import jbs.capitalismapi.types.stocks.orders.StockBuyOrder;
import jbs.capitalismapi.types.stocks.orders.StockOrder;
import jbs.capitalismapi.types.stocks.orders.StockSellOrder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class StockListing {
    public StockListing(
            String symbol,
            double lastTradedPrice,
            int lastTradedVolume,
            ArrayList<StockOrder> orders
    ) {
        this.symbol = symbol;
        this.lastTradedPrice = lastTradedPrice;
        this.lastTradedVolume = lastTradedVolume;
        this.orders = orders;
    }
    public StockListing(String symbol) {
        this.symbol = symbol;
        lastTradedPrice = 0d;
        lastTradedVolume = 0;
        orders = new ArrayList<StockOrder>();
    }
    public StockListing(StockListing sl) {
        symbol = sl.symbol;
        lastTradedPrice = sl.lastTradedPrice;
        lastTradedVolume = sl.lastTradedVolume;
        orders = sl.orders;
    }
    public StockListing() {
        symbol = null;
        lastTradedPrice = 0d;
        lastTradedVolume = 0;
        orders = new ArrayList<StockOrder>();
    }
    String symbol;
    double lastTradedPrice;
    int lastTradedVolume;
    ArrayList<StockOrder> orders;

    SecuritiesExchange exchange = null;

    public SecuritiesExchange getExchange() {
        return exchange;
    }

    public void setExchange(SecuritiesExchange exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public int getLastTradedVolume() {
        return lastTradedVolume;
    }

    public ArrayList<StockOrder> getOrders() {
        return orders;
    }

    public ArrayList<StockBuyOrder> getBuyOrders() {
        ArrayList<StockBuyOrder> buyOrders = new ArrayList<StockBuyOrder>();

        for (StockOrder o : orders) {
            if (o instanceof StockBuyOrder) {
                buyOrders.add((StockBuyOrder) o);
            }
        }

        return buyOrders;
    }

    public ArrayList<StockSellOrder> getSellOrders() {
        ArrayList<StockSellOrder> sellOrders = new ArrayList<StockSellOrder>();

        for (StockOrder o : orders) {
            if (o instanceof StockSellOrder) {
                sellOrders.add((StockSellOrder) o);
            }
        }

        return sellOrders;
    }

    public void processOrders() {
        ArrayList<StockBuyOrder> buyOrders = getBuyOrders();
        ArrayList<StockSellOrder> sellOrders = getSellOrders();

        buyOrders.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        sellOrders.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));

        for (StockBuyOrder bo : buyOrders) {
            if (bo.getQuantity() > 0) {
                for (StockSellOrder so : sellOrders) {
                    if (so.getQuantity() > 0) {
                        if (bo.getPrice() >= so.getPrice()) {
                            double price = bo.getTime().before(so.getTime()) ? bo.getPrice() : so.getPrice();
                            int quantity = Math.min(bo.getQuantity(), so.getQuantity());

                            bo.onFulfilled(price, quantity);
                            so.onFulfilled(price, quantity);

                            StockPortfolioEntry stocks = new StockPortfolioEntry(getSymbol(), quantity);
                            stocks.setPurchasePrice(price);
                            stocks.setPurchaseDate(new Date());

                            bo.getSender().getStockPortfolio().addEntry(stocks);

                            double sellSideFee = price * quantity * so.getFeeRate();
                            so.getSender().changeBalance(-sellSideFee);
                            exchange.changeBalance(sellSideFee);

                            this.lastTradedPrice = price;
                            this.lastTradedVolume = quantity;
                        }
                    }
                }
            }
        }

        ArrayList<Integer> indicesToClean = new ArrayList<Integer>();
        for (StockOrder o : orders) {
            if (o.getQuantity() <= 0) {
                indicesToClean.add(orders.indexOf(o));
            }
        }

        indicesToClean.sort(Comparator.reverseOrder());

        for (int i : indicesToClean) {
            orders.remove(i);
        }
    }

    public void addOrder(StockOrder order) {
        orders.add(order);
        exchange.changeBalance(order.getFeePaid());
    }

    public void cancelOrder(StockOrder order) {
        order.onCancelled();
        orders.remove(order);
    }

    public StockListingData toData() {
        StockListingData sld = new StockListingData();

        sld.symbol = symbol;
        sld.lastTradedPrice = lastTradedPrice;
        sld.lastTradedVolume = lastTradedVolume;
        for (StockOrder so : orders) {
            sld.orderData.add(so.toData());
        }

        return sld;
    }

    public static StockListing fromData(StockListingData data, Capitalism plugin) {
        ArrayList<StockOrder> stockOrders = new ArrayList<StockOrder>();
        for (StockOrderData sod : data.orderData) {
            stockOrders.add(StockOrder.fromData(sod, plugin));
        }

        return new StockListing(
                data.symbol,
                data.lastTradedPrice,
                data.lastTradedVolume,
                stockOrders
        );
    }
}
