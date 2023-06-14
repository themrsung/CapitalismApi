package jbs.capitalismapi.types.entities.player;

import jbs.capitalism.savetypes.entities.EconomicEntityType;
import jbs.capitalism.savetypes.entities.player.PlayerData;
import jbs.capitalism.types.entities.EconomicEntity;
import jbs.capitalism.types.entities.companies.CommodityExchange;
import jbs.capitalism.types.offers.JobOffer;
import jbs.capitalism.types.stocks.StockPortfolio;
import jbs.capitalism.types.trading.CommodityPortfolio;
import jbs.capitalism.types.trading.CommodityPortfolioEntry;
import jbs.capitalism.types.trading.orders.CommodityOrder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CapitalismPlayer extends EconomicEntity {
    public CapitalismPlayer(
        UUID uuid,
        String name,
        double balance,
        StockPortfolio stockPortfolio
    ) {
        super(uuid, name, balance, stockPortfolio);
    }
    public CapitalismPlayer(
            Player player
    ) {
        super(player.getUniqueId(), player.getName(), 86400d, new StockPortfolio());
    }
    public CapitalismPlayer(UUID uuid) {
        super(uuid);
    }
    public CapitalismPlayer(CapitalismPlayer cp) {
        super(cp);
        previousLocation = cp.previousLocation;
        premium = cp.premium;
        premiumExpiryDate = cp.premiumExpiryDate;
        lastKnownRank = cp.lastKnownRank;
        inboundJobOffers = cp.inboundJobOffers;
        lastKnownInventoryValue = cp.lastKnownInventoryValue;
        lastKnownEnderChestValue = cp.lastKnownEnderChestValue;
        lastKnownItemOrderCollateralValue = cp.lastKnownItemOrderCollateralValue;
        unreceivedCommodityPortfolio = cp.unreceivedCommodityPortfolio;
    }
    public CapitalismPlayer() {
        super();
    }

    public Player toPlayer() {
        return Bukkit.getPlayer(getUuid());
    }

    CapitalismPlayerRank lastKnownRank = CapitalismPlayerRank.UNEMPLOYED;

    public CapitalismPlayerRank getLastKnownRank() {
        return lastKnownRank;
    }

    public void setLastKnownRank(CapitalismPlayerRank lastKnownRank) {
        this.lastKnownRank = lastKnownRank;
    }

    @Nullable
    public transient Location previousLocation = null;

    boolean premium = false;

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Nullable
    Date premiumExpiryDate = null;

    @Nullable
    public Date getPremiumExpiryDate() {
        return premiumExpiryDate;
    }

    public void setPremiumExpiryDate(@Nullable Date premiumExpiryDate) {
        this.premiumExpiryDate = premiumExpiryDate;
    }

    public ArrayList<JobOffer> inboundJobOffers = new ArrayList<JobOffer>();

    double lastKnownInventoryValue = 0d;

    public double getLastKnownInventoryValue() {
        return lastKnownInventoryValue;
    }

    public void setLastKnownInventoryValue(double lastKnownInventoryValue) {
        this.lastKnownInventoryValue = lastKnownInventoryValue;
    }

    double lastKnownEnderChestValue = 0d;

    public double getLastKnownEnderChestValue() {
        return lastKnownEnderChestValue;
    }

    public void setLastKnownEnderChestValue(double lastKnownEnderChestValue) {
        this.lastKnownEnderChestValue = lastKnownEnderChestValue;
    }

    double lastKnownItemOrderCollateralValue = 0d;

    public double getLastKnownItemOrderCollateralValue() {
        return lastKnownItemOrderCollateralValue;
    }

    public void setLastKnownItemOrderCollateralValue(double lastKnownItemOrderCollateralValue) {
        this.lastKnownItemOrderCollateralValue = lastKnownItemOrderCollateralValue;
    }

    @Override
    public double getAssetValue() {
        return
                super.getAssetValue()
                + lastKnownInventoryValue
                + lastKnownEnderChestValue
                + lastKnownItemOrderCollateralValue;
    }

    @Override
    public void updateNetWorth() {
        super.updateNetWorth();

        Player player = toPlayer();
        if (player != null) {
            CommodityPortfolio i = new CommodityPortfolio();
            CommodityPortfolio ec = new CommodityPortfolio();

            for (ItemStack is : player.getInventory().getContents()) {
                if (is != null) {
                    i.addEntry(new CommodityPortfolioEntry(is.getType(), is.getAmount()));
                }
            }

            for (ItemStack is : player.getEnderChest().getContents()) {
                if (is != null) {
                    ec.addEntry(new CommodityPortfolioEntry(is.getType(), is.getAmount()));
                }
            }

            lastKnownInventoryValue = getPlugin().tradeTicker.getMarketValue(i);
            lastKnownEnderChestValue = getPlugin().tradeTicker.getMarketValue(ec);
        }

        double itemOrderCollateral = 0d;
        for (CommodityExchange ce : getPlugin().getCommodityExchanges()) {
            for (CommodityOrder co : ce.getOrders()) {
                itemOrderCollateral += co.getMoneyCollateral();
                if (co.getMaterialCollateral() != null) {
                    itemOrderCollateral += getPlugin().tradeTicker.getMarketValue(co.getMaterialCollateral());
                }
            }
        }
        lastKnownItemOrderCollateralValue = itemOrderCollateral;
    }

    CommodityPortfolio unreceivedCommodityPortfolio = new CommodityPortfolio();

    public CommodityPortfolio getUnreceivedCommodityPortfolio() {
        return unreceivedCommodityPortfolio;
    }

    public void setUnreceivedCommodityPortfolio(CommodityPortfolio unreceivedCommodityPortfolio) {
        this.unreceivedCommodityPortfolio = unreceivedCommodityPortfolio;
    }

    @Override
    public PlayerData toData() {
        PlayerData pd = new PlayerData(super.toData());

        pd.type = EconomicEntityType.PLAYER;
        pd.lastKnownRank = lastKnownRank;
        pd.premium = premium;
        pd.premiumExpiryDate = premiumExpiryDate;
        pd.lastKnownInventoryValue = lastKnownInventoryValue;
        pd.lastKnownEnderChestValue = lastKnownEnderChestValue;
        pd.lastKnownItemOrderCollateralValue = lastKnownItemOrderCollateralValue;
        pd.unreceivedCommodityPortfolioData = unreceivedCommodityPortfolio.toData();

        return pd;
    }

    public static CapitalismPlayer fromData(PlayerData data) {
        return new CapitalismPlayer(UUID.fromString(data.uuid));
    }
}
