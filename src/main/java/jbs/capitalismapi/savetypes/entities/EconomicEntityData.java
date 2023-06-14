package jbs.capitalismapi.savetypes.entities;

import jbs.capitalismapi.savetypes.bonds.BondData;
import jbs.capitalismapi.savetypes.bonds.BondOfferData;
import jbs.capitalismapi.savetypes.navigation.LocationData;
import jbs.capitalismapi.savetypes.stocks.StockPortfolioData;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EconomicEntityData {
    public EconomicEntityData(EconomicEntityData eed) {
        this.type = eed.type;
        this.uuid = eed.uuid;
        this.name = eed.name;
        this.balance = eed.balance;
        this.stockPortfolioData = eed.stockPortfolioData;
        this.lastKnownStockOrderCollateralValue = eed.lastKnownStockOrderCollateralValue;
        this.bondPortfolioData = eed.bondPortfolioData;
        this.outstandingLendOfferData = eed.outstandingLendOfferData;
        this.outstandingBorrowOfferData = eed.outstandingBorrowOfferData;
        this.lastKnownStockPortfolioValue = eed.lastKnownStockPortfolioValue;
        this.address = eed.address;
    }
    public EconomicEntityData() {}
    public EconomicEntityType type = null;
    public String uuid = null;
    public String name = null;
    public double balance = 0d;

    public double lastKnownStockPortfolioValue = 0d;
    public double lastKnownStockOrderCollateralValue = 0d;
    public ArrayList<BondData> bondPortfolioData = new ArrayList<BondData>();
    public ArrayList<BondOfferData> outstandingLendOfferData = new ArrayList<BondOfferData>();
    public ArrayList<BondOfferData> outstandingBorrowOfferData = new ArrayList<BondOfferData>();
    public StockPortfolioData stockPortfolioData = new StockPortfolioData();
    @Nullable
    public LocationData address = null;
}
