package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.stocks.StockPortfolioEntry;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockIssueMeeting extends Meeting{
    public StockIssueMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s,
            int numberOfNewSharesToIssue
    ) {
        super(c, d, tvs, procv, protv, s);
        this.numberOfNewSharesToIssue = numberOfNewSharesToIssue;

    }
    public StockIssueMeeting() {
        super();
    }

    int numberOfNewSharesToIssue = 0;

    public int getNumberOfNewSharesToIssue() {
        return numberOfNewSharesToIssue;
    }

    @Override
    public String getAgenda() {
        return "신주 " + NumberFormat.getIntegerInstance().format(numberOfNewSharesToIssue) + "주 발행의 건";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        company.getStockPortfolio().addEntry(new StockPortfolioEntry(company.getSymbol(), numberOfNewSharesToIssue));
        company.changeShareCount(numberOfNewSharesToIssue);
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.numberOfNewSharesToIssue = numberOfNewSharesToIssue;
        return md;
    }

    public static StockIssueMeeting fromData(MeetingData data, Capitalism plugin) {
        StockIssueMeeting sim = new StockIssueMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                new ArrayList<MeetingShareholder>(),
                data.numberOfNewSharesToIssue
        );
        for (MeetingShareholderData msd : data.votableShareholderData) {
            sim.votableShareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return sim;

    }
}
