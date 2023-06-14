package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.classes.economy.Money;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.entities.companies.Company;

import java.util.ArrayList;
import java.util.Date;

public class CashDividendMeeting extends Meeting {
    public CashDividendMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s,
            double dps
    ) {
        super(c, d, tvs, procv, protv, s);
        dividendPerShare = dps;
    }
    public CashDividendMeeting() {
        super();
    }
    double dividendPerShare = 0d;

    public double getDividendPerShare() {
        return dividendPerShare;
    }

    @Override
    public String getAgenda() {
        return "주당 " + new Money(dividendPerShare).formatCommas() + "현금배당의 건";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        for (EconomicEntity e : plugin.getEntities()) {
            int q = e.getStockPortfolio().getQuantityOf(company.getSymbol());
            if (q > 0) {
                double d = q * dividendPerShare;
                double t = d * plugin.config.dividendTaxRate;

                company.changeBalance(-d);
                e.changeBalance(d - t);
                if (plugin.getServerEntity() != null) plugin.getServerEntity().changeBalance(t);
            }
        }
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.dividendPerShare = dividendPerShare;
        return md;
    }

    public static CashDividendMeeting fromData(MeetingData data, Capitalism plugin) {
        ArrayList<MeetingShareholder> shareholders = new ArrayList<MeetingShareholder>();
        for (MeetingShareholderData msd : data.votableShareholderData) {
            shareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return new CashDividendMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                shareholders,
                data.dividendPerShare
        );
    }
}
