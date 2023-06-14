package jbs.capitalismapi.savetypes.entities;

import jbs.capitalismapi.savetypes.bonds.BondApiData;
import jbs.capitalismapi.savetypes.navigation.LocationApiData;
import jbs.capitalismapi.savetypes.stocks.StockPortfolioApiData;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EconomicEntityApiData {
    public EconomicEntityApiType type = null;
    public String uuid = null;
    public String name = null;
    public double balance = 0d;
    public StockPortfolioApiData stockPortfolioData = new StockPortfolioApiData();
    public ArrayList<BondApiData> bondPortfolioDate = new ArrayList<BondApiData>();
    public double stockOrderCollateralValue = 0d;

    @Nullable public LocationApiData addressData = null;
}
