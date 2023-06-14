package jbs.capitalismapi.types.entities;

import jbs.capitalismapi.savetypes.entities.EconomicEntityData;
import jbs.capitalismapi.savetypes.entities.EconomicEntityType;
import jbs.capitalismapi.savetypes.navigation.LocationData;
import jbs.capitalismapi.types.bonds.Bond;
import jbs.capitalismapi.types.bonds.BorrowOffer;
import jbs.capitalismapi.types.bonds.LendOffer;
import jbs.capitalismapi.types.entities.companies.SecuritiesExchange;
import jbs.capitalismapi.types.stocks.StockListing;
import jbs.capitalismapi.types.stocks.StockPortfolio;
import jbs.capitalismapi.types.stocks.orders.StockOrder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class EconomicEntity {
    public EconomicEntity(
            UUID uuid,
            String name,
            double balance,
            StockPortfolio stockPortfolio
    ) {
        this.uuid = uuid;
        this.name = name;
        this.balance = balance;
        this.stockPortfolio = stockPortfolio;
    }
    public EconomicEntity(EconomicEntity ee) {
        uuid = ee.uuid;
        name = ee.name;
        balance = ee.balance;
        stockPortfolio = ee.stockPortfolio;
        plugin = ee.plugin;
        lastKnownStockPortfolioValue = ee.lastKnownStockPortfolioValue;
        lastKnownStockOrderCollateralValue = ee.lastKnownStockOrderCollateralValue;
        bondPortfolio = ee.bondPortfolio;
        outstandingDebt = ee.outstandingDebt;
        outstandingLendOffers = ee.outstandingLendOffers;
        inboundLendOffers = ee.inboundLendOffers;
        outstandingBorrowOffers = ee.outstandingBorrowOffers;
        inboundBorrowOffers = ee.inboundBorrowOffers;
        address = ee.address;
    }
    public EconomicEntity(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }
    public EconomicEntity(UUID uuid) {
        this.uuid = uuid;
    }
    public EconomicEntity() {}

    UUID uuid = null;
    String name = null;
    double balance = 0d;
    StockPortfolio stockPortfolio = new StockPortfolio();

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public StockPortfolio getStockPortfolio() {
        return stockPortfolio;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void changeBalance(double delta) {
        this.balance += delta;
    }

    public void setStockPortfolio(StockPortfolio stockPortfolio) {
        this.stockPortfolio = stockPortfolio;
    }

    double lastKnownStockPortfolioValue = 0d;

    public double getLastKnownStockPortfolioValue() {
        return lastKnownStockPortfolioValue;
    }

    public void setLastKnownStockPortfolioValue(double lastKnownStockPortfolioValue) {
        this.lastKnownStockPortfolioValue = lastKnownStockPortfolioValue;
    }

    double lastKnownStockOrderCollateralValue = 0d;

    public double getLastKnownStockOrderCollateralValue() {
        return lastKnownStockOrderCollateralValue;
    }

    public void setLastKnownStockOrderCollateralValue(double lastKnownStockOrderCollateralValue) {
        this.lastKnownStockOrderCollateralValue = lastKnownStockOrderCollateralValue;
    }

    public double getAssetValue() {
        return
                balance
                + lastKnownStockPortfolioValue
                + lastKnownStockOrderCollateralValue
                + getBondPortfolioValue();
    }

    public double getLiabilities() {
        return getOutstandingDebtValue();
    }

    public double getNetWorth() {
        return getAssetValue() - getLiabilities();
    }

    public void updateNetWorth() {
        lastKnownStockPortfolioValue =  plugin.stockTicker.getMarketValue(stockPortfolio);

        lastKnownStockOrderCollateralValue = 0d;
        for (SecuritiesExchange se : plugin.getSecuritiesExchanges()) {
            for (StockListing sl : se.getStockListings()) {
                for (StockOrder so: sl.getOrders()) {
                    if (so.getSender().equals(this)) {
                        lastKnownStockOrderCollateralValue += so.getMoneyCollateral();
                        lastKnownStockOrderCollateralValue += plugin.stockTicker.getMarketValue(so.getStockCollateral());
                    }
                }
            }
        }

    }

    ArrayList<Bond> bondPortfolio = new ArrayList<Bond>();
    public ArrayList<Bond> outstandingDebt = new ArrayList<Bond>();

    public double getOutstandingDebtValue() {
        double v = 0d;

        for (Bond b : outstandingDebt) {
            v += b.getAmount();
        }

        return v;
    }

    public ArrayList<Bond> getBondPortfolio() {
        return bondPortfolio;
    }

    public double getBondPortfolioValue() {
        double v = 0d;

        for (Bond b : bondPortfolio) {
            v += b.getAmount();
        }

        return v;
    }

    public void addBond(Bond b) {
        bondPortfolio.add(b);
    }

    public void transferBond(EconomicEntity target, Bond b) {
        b.setCreditor(target);
        removeBond(b);
        target.addBond(b);
    }

    public void removeBond(Bond b) {
        bondPortfolio.remove(b);
    }

    public void setBondPortfolio(ArrayList<Bond> bondPortfolio) {
        this.bondPortfolio = bondPortfolio;
    }

    ArrayList<LendOffer> outstandingLendOffers = new ArrayList<LendOffer>();
    public ArrayList<LendOffer> inboundLendOffers = new ArrayList<LendOffer>();

    public ArrayList<LendOffer> getOutstandingLendOffers() {
        return outstandingLendOffers;
    }

    public void addOutstandingLendOffer(LendOffer lo) {
        outstandingLendOffers.add(lo);
    }

    public void removeOutstandingLendOffer(LendOffer lo) {
        outstandingLendOffers.remove(lo);
    }

    public void setOutstandingLendOffers(ArrayList<LendOffer> outstandingLendOffers) {
        this.outstandingLendOffers = outstandingLendOffers;
    }

    ArrayList<BorrowOffer> outstandingBorrowOffers = new ArrayList<BorrowOffer>();
    public ArrayList<BorrowOffer> inboundBorrowOffers = new ArrayList<BorrowOffer>();

    public ArrayList<BorrowOffer> getOutstandingBorrowOffers() {
        return outstandingBorrowOffers;
    }
    public void addOutstandingBorrowOffer(BorrowOffer bo) {
        outstandingBorrowOffers.add(bo);
    }

    public void removeOutstandingBorrowOffer(BorrowOffer bo) {
        outstandingBorrowOffers.remove(bo);
    }

    public void setOutstandingBorrowOffers(ArrayList<BorrowOffer> outstandingBorrowOffers) {
        this.outstandingBorrowOffers = outstandingBorrowOffers;
    }

    Location address = null;

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public boolean equals(EconomicEntity ee) {
        return uuid.equals(ee.uuid);
    }

    Capitalism plugin;

    public Capitalism getPlugin() {
        return plugin;
    }

    public void setPlugin(Capitalism plugin) {
        this.plugin = plugin;
    }

    public EconomicEntityData toData() {
        EconomicEntityData e = new EconomicEntityData();

        e.type = EconomicEntityType.ECONOMIC_ENTITY;
        e.uuid = uuid.toString();
        e.name = name;
        e.balance = balance;
        e.stockPortfolioData = stockPortfolio.toData();

        e.lastKnownStockPortfolioValue = lastKnownStockPortfolioValue;
        e.lastKnownStockOrderCollateralValue = lastKnownStockOrderCollateralValue;

        if (address != null)  e.address = new LocationData(address);

        for (Bond b : bondPortfolio) {
            e.bondPortfolioData.add(b.toData());
        }

        for (LendOffer o : outstandingLendOffers) {
            e.outstandingLendOfferData.add(o.toData());
        }

        for (BorrowOffer o : outstandingBorrowOffers) {
            e.outstandingBorrowOfferData.add(o.toData());
        }

        return e;
    }

    public static EconomicEntity fromData(EconomicEntityData data) {
        return new EconomicEntity(UUID.fromString(data.uuid));
    }
}
