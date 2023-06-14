package jbs.capitalismapi.types.entities;

import jbs.capitalismapi.types.bonds.BondApi;
import jbs.capitalismapi.types.stocks.StockPortfolioApi;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class EconomicEntityApi {
    UUID uuid = null;
    String name = null;
    double balance = 0d;
    StockPortfolioApi stockPortfolio = new StockPortfolioApi();
    ArrayList<BondApi> bondPortfolio = new ArrayList<BondApi>();
    double stockOrderCollateralValue = 0d;
    @Nullable
    Location address = null;

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public StockPortfolioApi getStockPortfolio() {
        return stockPortfolio;
    }

    public ArrayList<BondApi> getBondPortfolio() {
        return bondPortfolio;
    }

    public double getStockOrderCollateralValue() {
        return stockOrderCollateralValue;
    }

    @Nullable
    public Location getAddress() {
        return address;
    }
}
