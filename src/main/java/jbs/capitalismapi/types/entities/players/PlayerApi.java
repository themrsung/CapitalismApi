package jbs.capitalismapi.types.entities.players;

import jbs.capitalismapi.savetypes.entities.players.PlayerApiData;
import jbs.capitalismapi.types.entities.EconomicEntityApi;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;

public class PlayerApi extends EconomicEntityApi {
    // Used in fromData()
    public PlayerApi(UUID uuid) {
        super(uuid);
    }
    public PlayerApi() { super(); }

    // Player's in-game rank
    // 랭크
    PlayerApiRank rank = PlayerApiRank.UNEMPLOYED;

    // Whether user is premium
    // 프리미엄 유저 여부
    boolean premium = false;

    // Premium expiration (Lifetime if null)
    // 프리미엄 만기일 (null일 경우 평생권)
    @Nullable
    Date premiumExpiryDate = null;

    // Current market value of player's inventory
    // 현재 인벤토리의 시장 평가액
    double inventoryValue = 0d;

    // Current market value of player's ender chest
    // 현재 엔더체스트의 시장 평가액
    double enderChestValue = 0d;

    // Current market value of collateral held in item buy/sell orders sent by this player
    // 미체결 아이템 주문에 잡혀있는 담보의 시장가치
    double itemOrderCollateralValue = 0d;

    // Current market value of all commodities player has bought or been gifted to but not received.
    // 매수나 수증의 사유로 상품을 받았지만 아직 거래소에서 인도하지 않은 경우 미인도분의 시장가치
    double unreceivedCommodityPortfolioValue = 0d;

    public PlayerApiRank getRank() {
        return rank;
    }

    public void setRank(PlayerApiRank rank) {
        this.rank = rank;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Nullable
    public Date getPremiumExpiryDate() {
        return premiumExpiryDate;
    }

    public void setPremiumExpiryDate(@Nullable Date premiumExpiryDate) {
        this.premiumExpiryDate = premiumExpiryDate;
    }

    public double getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(double inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    public double getEnderChestValue() {
        return enderChestValue;
    }

    public void setEnderChestValue(double enderChestValue) {
        this.enderChestValue = enderChestValue;
    }

    public double getItemOrderCollateralValue() {
        return itemOrderCollateralValue;
    }

    public void setItemOrderCollateralValue(double itemOrderCollateralValue) {
        this.itemOrderCollateralValue = itemOrderCollateralValue;
    }

    public double getUnreceivedCommodityPortfolioValue() {
        return unreceivedCommodityPortfolioValue;
    }

    public void setUnreceivedCommodityPortfolioValue(double unreceivedCommodityPortfolioValue) {
        this.unreceivedCommodityPortfolioValue = unreceivedCommodityPortfolioValue;
    }

    public PlayerApiData toData() {
        PlayerApiData data = new PlayerApiData(super.toData());

        data.rank = rank;
        data.premium = premium;
        data.premiumExpiryDate = premiumExpiryDate;
        data.inventoryValue = inventoryValue;
        data.enderChestValue = enderChestValue;
        data.itemOrderCollateralValue = itemOrderCollateralValue;
        data.unreceivedCommodityPortfolioValue = unreceivedCommodityPortfolioValue;

        return data;
    }

    public static PlayerApi fromData(PlayerApiData data) {
        return new PlayerApi(UUID.fromString(data.uuid));
    }
}
