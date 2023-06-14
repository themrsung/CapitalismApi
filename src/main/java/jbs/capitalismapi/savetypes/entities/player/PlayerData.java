package jbs.capitalismapi.savetypes.entities.player;

import jbs.capitalism.savetypes.entities.EconomicEntityData;
import jbs.capitalism.savetypes.trading.CommodityPortfolioData;
import jbs.capitalism.types.entities.player.CapitalismPlayerRank;

import javax.annotation.Nullable;
import java.util.Date;

public class PlayerData extends EconomicEntityData {
    public PlayerData(EconomicEntityData eed) {
        super(eed);
    }
    public PlayerData() {
        super();
    }

    public CapitalismPlayerRank lastKnownRank = CapitalismPlayerRank.UNEMPLOYED;

    public boolean premium = false;
    @Nullable
    public Date premiumExpiryDate = null;
    public double lastKnownInventoryValue = 0d;
    public double lastKnownEnderChestValue = 0d;
    public double lastKnownItemOrderCollateralValue = 0d;
    public CommodityPortfolioData unreceivedCommodityPortfolioData = new CommodityPortfolioData();
}
