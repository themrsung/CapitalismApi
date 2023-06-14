package jbs.capitalismapi.savetypes.entities.players;

import jbs.capitalismapi.savetypes.entities.EconomicEntityApiData;
import jbs.capitalismapi.types.entities.players.PlayerApiRank;

import javax.annotation.Nullable;
import java.util.Date;

public class PlayerApiData extends EconomicEntityApiData {
    public PlayerApiRank rank = PlayerApiRank.UNEMPLOYED;
    public boolean premium = false;
    @Nullable
    public Date premiumExpiryDate = null;
    public double inventoryValue = 0d;
    public double enderChestValue = 0d;
    public double itemOrderCollateralValue = 0d;
    public double unreceivedCommodityPortfolioValue = 0d;
}
