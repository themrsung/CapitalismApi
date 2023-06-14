package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.stocks.StockPortfolioEntry;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockRetireMeeting extends Meeting {
    public StockRetireMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s,
            int numberOfStocksToRetire
    ) {
        super(c, d, tvs, procv, protv, s);
        this.numberOfStocksToRetire = numberOfStocksToRetire;
    }
    public StockRetireMeeting() {
        super();
    }

    int numberOfStocksToRetire = 0;

    public int getNumberOfStocksToRetire() {
        return numberOfStocksToRetire;
    }

    @Override
    public String getAgenda() {
        return "자사주 " + NumberFormat.getIntegerInstance().format(numberOfStocksToRetire) + "주 소각의 건";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        company.getStockPortfolio().removeEntry(new StockPortfolioEntry(company.getSymbol(), numberOfStocksToRetire));
        company.changeShareCount(-numberOfStocksToRetire);
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.numberOfStocksToRetire = numberOfStocksToRetire;
        return md;
    }

    public static StockRetireMeeting fromData(MeetingData data, Capitalism plugin) {
        StockRetireMeeting srm = new StockRetireMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                new ArrayList<MeetingShareholder>(),
                data.numberOfStocksToRetire
        );
        for (MeetingShareholderData msd : data.votableShareholderData) {
            srm.votableShareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return srm;
    }
}
