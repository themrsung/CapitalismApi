package jbs.capitalismapi.types.entities.players;

import javax.annotation.Nullable;
import java.util.Date;

public class PlayerApi {
    PlayerApiRank rank = PlayerApiRank.UNEMPLOYED;
    boolean premium = false;
    @Nullable
    Date premiumExpiryDate = null;
    double inventoryValue = 0d;
    double enderChestValue = 0d;
    double itemOrderCollateralValue = 0d;
    double unreceivedCommodityPortfolioValue = 0d;
}
