package jbs.capitalismapi.io;

import jbs.capitalismapi.CapitalismApi;
import jbs.capitalismapi.savetypes.bonds.BondApiData;
import jbs.capitalismapi.savetypes.entities.EconomicEntityApiData;
import jbs.capitalismapi.savetypes.entities.EconomicEntityApiType;
import jbs.capitalismapi.savetypes.entities.companies.CompanyApiData;
import jbs.capitalismapi.savetypes.entities.players.PlayerApiData;
import jbs.capitalismapi.types.bonds.BondApi;
import jbs.capitalismapi.types.entities.EconomicEntityApi;
import jbs.capitalismapi.types.entities.companies.CompanyApi;
import jbs.capitalismapi.types.entities.players.PlayerApi;
import jbs.capitalismapi.types.stocks.StockPortfolioApi;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.UUID;

public class ApiDataInitializer {
    public ApiDataInitializer(CapitalismApi plugin) {
        this.plugin = plugin;
    }
    CapitalismApi plugin;

    public void initializeEntities(ArrayList<EconomicEntityApiData> entityData) {
        for (EconomicEntityApi e : plugin.getEntities()) {
            for (EconomicEntityApiData ed : entityData) {
                if (e.getUuid().equals(ed.uuid)) {
                    // Same entity, initialize data

                    e.setName(ed.name);
                    e.setBalance(ed.balance);
                    e.setStockPortfolio(StockPortfolioApi.fromData(ed.stockPortfolioData));

                    for (BondApiData bad : ed.bondPortfolioData) {
                        e.getBondPortfolio().add(BondApi.fromData(bad, plugin));
                    }

                    e.setStockOrderCollateralValue(ed.stockOrderCollateralValue);

                    if (ed.addressData != null) {
                        World w = Bukkit.getWorld(ed.addressData.world);
                        if (w != null) {
                            e.setAddress(new Location(
                                    w,
                                    ed.addressData.x,
                                    ed.addressData.y,
                                    ed.addressData.z,
                                    ed.addressData.yaw,
                                    ed.addressData.pitch
                            ));
                        }
                    }

                    if (ed.type == EconomicEntityApiType.PLAYER_API) {
                        PlayerApi p = (PlayerApi) e;
                        PlayerApiData pd = (PlayerApiData) ed;

                        p.setRank(pd.rank);
                        p.setPremium(pd.premium);
                        p.setPremiumExpiryDate(pd.premiumExpiryDate);
                        p.setInventoryValue(pd.inventoryValue);
                        p.setEnderChestValue(pd.enderChestValue);
                        p.setItemOrderCollateralValue(pd.itemOrderCollateralValue);
                        p.setUnreceivedCommodityPortfolioValue(pd.unreceivedCommodityPortfolioValue);
                    } else if (ed.type == EconomicEntityApiType.COMPANY_API) {
                        CompanyApi c = (CompanyApi) e;
                        CompanyApiData cd = (CompanyApiData) ed;

                        c.setSymbol(cd.symbol);
                        c.setDescription(cd.description);
                        c.setCapital(cd.capital);
                        c.setShareCount(cd.shareCount);

                        for (String uuid : cd.employeeUuids) {
                            c.getEmployees().add(plugin.getPlayer(UUID.fromString(uuid)));
                        }

                        for (String uuid : cd.directorUuids) {
                            c.getDirectors().add(plugin.getPlayer(UUID.fromString(uuid)));
                        }

                        if (cd.ceoUuid != null) {
                            c.setCeo(plugin.getPlayer(UUID.fromString(cd.ceoUuid)));
                        }
                    }
                }
            }
        }
    }
}
