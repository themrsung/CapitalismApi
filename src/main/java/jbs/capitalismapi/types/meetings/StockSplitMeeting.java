package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.stocks.StockPortfolioEntry;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockSplitMeeting extends Meeting {

    public StockSplitMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s,
            int numberOfNewSharesPerExistingShare
    ) {
        super(c, d, tvs, procv, protv, s);
        this.numberOfNewSharesPerExistingShare = numberOfNewSharesPerExistingShare;

    }

    int numberOfNewSharesPerExistingShare;

    public int getNumberOfNewSharesPerExistingShare() {
        return numberOfNewSharesPerExistingShare;
    }

    @Override
    public String getAgenda() {
        return "주식 분할의 건 (주당 " + NumberFormat.getIntegerInstance().format(numberOfNewSharesPerExistingShare) + "주 발행)";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        int t = 0;
        for (EconomicEntity e : plugin.getEntities()) {
            int q = e.getStockPortfolio().getQuantityOf(company.getSymbol());
            int n = q * numberOfNewSharesPerExistingShare;
            t += n;

            if (n > 0) {
                e.getStockPortfolio().addEntry(new StockPortfolioEntry(company.getSymbol(), n));
            }
        }

        company.changeShareCount(t);
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.numberOfNewSharesPerExistingShare = numberOfNewSharesPerExistingShare;
        return md;
    }

    public static StockSplitMeeting fromData(MeetingData data, Capitalism plugin) {
        StockSplitMeeting ssm = new StockSplitMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                new ArrayList<MeetingShareholder>(),
                data.numberOfNewSharesPerExistingShare
        );
        for (MeetingShareholderData msd : data.votableShareholderData) {
            ssm.votableShareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return ssm;
    }
}
