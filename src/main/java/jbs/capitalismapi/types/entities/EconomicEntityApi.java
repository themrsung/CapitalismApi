package jbs.capitalismapi.types.entities;

import jbs.capitalismapi.savetypes.entities.EconomicEntityApiData;
import jbs.capitalismapi.savetypes.navigation.LocationApiData;
import jbs.capitalismapi.types.bonds.BondApi;
import jbs.capitalismapi.types.stocks.StockPortfolioApi;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

// Economic Entity
// This is a parent class of both players and companies
//
// 경제주체
// 플레이어와 회사의 공통된 부모 클래스
public class EconomicEntityApi {
    public EconomicEntityApi(
            UUID uuid,
            String name,
            double balance,
            StockPortfolioApi stockPortfolio,
            ArrayList<BondApi> bondPortfolio,
            double stockOrderCollateralValue,
            @Nullable Location address
    ) {
        this.uuid = uuid;
        this.name = name;
        this.balance = balance;
        this.stockPortfolio = stockPortfolio;
        this.bondPortfolio = bondPortfolio;
        this.stockOrderCollateralValue = stockOrderCollateralValue;
        this.address = address;
    }
    // Used in fromData()
    public EconomicEntityApi(UUID uuid) {
        this.uuid = uuid;
    }
    public EconomicEntityApi(EconomicEntityApi entity) {
        uuid = entity.uuid;
        name = entity.name;
        balance = entity.balance;
        stockPortfolio = entity.stockPortfolio;
        bondPortfolio = entity.bondPortfolio;
        stockOrderCollateralValue = entity.stockOrderCollateralValue;
        address = entity.address;
    }
    public EconomicEntityApi() {}

    // UUID of this entity
    // 경제주체의 UUID
    UUID uuid = null;

    // Name of this entity
    // In-game name for players, company name for companies
    //
    // 경제주체의 이름
    // 플레이어의 경우 닉네임, 회사의 경우 회사명
    String name = null;

    // Balance of this entity
    // 경제주체의 현금잔고
    double balance = 0d;

    // Stock portfolio of this entity
    // 보유주식
    StockPortfolioApi stockPortfolio = new StockPortfolioApi();

    // Bond portfolio of this entity
    // 보유채권
    ArrayList<BondApi> bondPortfolio = new ArrayList<BondApi>();

    // Collateral held in outstanding stock buy/sell orders sent by this entity
    // 주식 미체결주문에 담보로 잡혀있는 증거금
    double stockOrderCollateralValue = 0d;

    // In-game address of this entity
    // Home for players, headquarters for companies
    //
    // 게임 내 주소
    // 플레이어의 경우 홈, 회사의 경우 본점
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setStockPortfolio(StockPortfolioApi stockPortfolio) {
        this.stockPortfolio = stockPortfolio;
    }

    public void setBondPortfolio(ArrayList<BondApi> bondPortfolio) {
        this.bondPortfolio = bondPortfolio;
    }

    public void setStockOrderCollateralValue(double stockOrderCollateralValue) {
        this.stockOrderCollateralValue = stockOrderCollateralValue;
    }

    public void setAddress(@Nullable Location address) {
        this.address = address;
    }

    public EconomicEntityApiData toData() {
        EconomicEntityApiData data = new EconomicEntityApiData();

        data.uuid = uuid.toString();
        data.name = name;
        data.balance = balance;
        data.stockPortfolioData = stockPortfolio.toData();

        for (BondApi bond : bondPortfolio) {
            data.bondPortfolioData.add(bond.toData());
        }

        data.stockOrderCollateralValue = stockOrderCollateralValue;
        data.addressData = new LocationApiData(
                address.getWorld().getName(),
                address.getX(),
                address.getY(),
                address.getZ(),
                address.getYaw(),
                address.getPitch()
        );

        return data;
    }

    public static EconomicEntityApi fromData(EconomicEntityApiData data) {
        return new EconomicEntityApi(UUID.fromString(data.uuid));
    }
}
