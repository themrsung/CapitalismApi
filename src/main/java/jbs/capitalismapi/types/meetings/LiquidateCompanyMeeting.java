package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.bonds.Bond;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.entities.companies.Bank;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.entities.companies.SecuritiesExchange;
import jbs.capitalismapi.types.stocks.StockListing;
import jbs.capitalismapi.types.stocks.StockPortfolio;
import jbs.capitalismapi.types.stocks.StockPortfolioEntry;
import jbs.capitalismapi.types.stocks.orders.StockOrder;

import java.util.ArrayList;
import java.util.Date;

public class LiquidateCompanyMeeting extends Meeting{
    public LiquidateCompanyMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s
    ) {
        super(c, d, tvs, procv, protv, s);
    }
    public LiquidateCompanyMeeting() {
        super();
    }

    @Override
    public String getAgenda() {
        return "회사 청산의 건";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        // Cancel all outstanding stock orders
        for (SecuritiesExchange se : plugin.getSecuritiesExchanges()) {
            for (StockListing sl : se.getStockListings()) {
                ArrayList<StockOrder> orders = new ArrayList<StockOrder>(sl.getOrders());
                for (StockOrder o : orders) {
                    if (o.getSender().equals(company)) {
                        sl.cancelOrder(o);
                    }
                }
            }
        }

        // Close all bank accounts
        for (Bank b : plugin.getBanks()) {
            b.withdraw(company, b.getBankWithdrawableBalance(company));
            b.getAccounts().removeIf(ba -> ba.getOwner().equals(company));
        }

        // Delist company from all stock exchanges
        for (SecuritiesExchange se : plugin.getSecuritiesExchanges()) {
            for (StockListing sl : se.getStockListings()) {
                if (sl.getSymbol().equalsIgnoreCase(company.getSymbol())) {
                    se.removeStockListing(sl);
                }
            }
        }

        // Retire all self owned shares
        int q = company.getStockPortfolio().getQuantityOf(company.getSymbol());
        company.getStockPortfolio().removeEntry(new StockPortfolioEntry(company.getSymbol(), q));
        company.changeShareCount(-q);

        // Forgive all bonds
        for (Bond b : company.getBondPortfolio()) {
            b.onForgiven();
        }

        // Default on all outstanding debt
        for (Bond b : company.outstandingDebt) {
            b.onDefaulted();
        }

        // Split stock portfolio to shareholders
        StockPortfolio remainder = new StockPortfolio();
        EconomicEntity majorityShareholder = plugin.getMajorityShareholderOf(company);

        for (StockPortfolioEntry spe : company.getStockPortfolio().getEntries()) {
            int sharesPerShare = (int) Math.floor((double) spe.getQuantity() / company.getShareCount());

            for (EconomicEntity e : plugin.getEntities()) {
                int q2 = e.getStockPortfolio().getQuantityOf(company.getSymbol());
                if (q2 > 0) {
                    int sharesToReceive = sharesPerShare * q2;

                    StockPortfolioEntry stocks = new StockPortfolioEntry(spe);
                    stocks.setQuantity(sharesToReceive);
                    e.getStockPortfolio().addEntry(stocks);
                    spe.changeQuantity(-sharesToReceive);
                }
            }

            int remainingShares = spe.getQuantity();
            if (remainingShares > 0) {
                remainder.addEntry(spe);
            }
        }

        // Remainder goes to majority shareholder (to server entity if company itself)
        if (!majorityShareholder.equals(company)) {
            majorityShareholder.getStockPortfolio().addPortfolio(remainder);
        } else {
            if (plugin.getServerEntity() != null) {
                plugin.getServerEntity().getStockPortfolio().addPortfolio(remainder);
            }
        }

        // Split balance to shareholders
        double payoutPerShare = Math.max(company.getBalance() / company.getShareCount(), 0);
        for (EconomicEntity e : plugin.getEntities()) {
            int q2 = e.getStockPortfolio().getQuantityOf(company.getSymbol());
            if (q2 >= 0) {
                e.changeBalance(payoutPerShare * q2);
            }
        }

        plugin.removeEntity(company);

    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        return md;
    }

    public static LiquidateCompanyMeeting fromData(MeetingData data, Capitalism plugin) {
        LiquidateCompanyMeeting lcm = new LiquidateCompanyMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                new ArrayList<MeetingShareholder>()
        );
        for (MeetingShareholderData msd : data.votableShareholderData) {
            lcm.votableShareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return lcm;
    }
}
