package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.entities.companies.Company;

import java.util.ArrayList;
import java.util.Date;

public class Meeting {
    public Meeting(
            Company company,
            Date date,
            int totalVotableShares,
            float passRatioOnCastedVotes,
            float passRatioOnTotalVotes,
            ArrayList<MeetingShareholder> votableShareholders
    ) {
        this.company = company;
        this.date = date;
        this.totalVotableShares = totalVotableShares;
        this.passRatioOnCastedVotes = passRatioOnCastedVotes;
        this.passRatioOnTotalVotes = passRatioOnTotalVotes;
        this.votableShareholders = votableShareholders;
    }
    Meeting() {
        company = null;
        date = null;
        totalVotableShares = 0;
        passRatioOnTotalVotes = 0f;
        passRatioOnTotalVotes = 0f;
        votableShareholders = new ArrayList<MeetingShareholder>();
    }

    Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    Date date;

    public Date getDate() {
        return date;
    }

    int totalVotableShares;

    public int getTotalVotableShares() {
        return totalVotableShares;
    }

    float passRatioOnCastedVotes;

    public float getPassRatioOnCastedVotes() {
        return passRatioOnCastedVotes;
    }

    float passRatioOnTotalVotes;

    public float getPassRatioOnTotalVotes() {
        return passRatioOnTotalVotes;
    }

    ArrayList<MeetingShareholder> votableShareholders;

    public ArrayList<MeetingShareholder> getVotableShareholders() {
        return votableShareholders;
    }


    public void vote(EconomicEntity shareholder, boolean isYes) {
        int quantity = getRemainingVotesOfShareholder(shareholder);

        for (MeetingShareholder ms : votableShareholders) {
            if (ms.shareholder.equals(shareholder)) {

                if (isYes) { ms.yesVotesCasted += quantity; }
                else { ms.noVotesCasted += quantity; }

            }
        }

    }

    public int getRemainingVotesOfShareholder(EconomicEntity shareholder) {
        for (MeetingShareholder ms : votableShareholders) {
            if (ms.shareholder.equals(shareholder)) {
                return ms.getRemainingVotes();
            }
        }

        return 0;
    }

    public int getTotalCastedYesVotes() {
        int v = 0;
        for (MeetingShareholder ms : votableShareholders) {
            v += ms.yesVotesCasted;
        }
        return v;
    }

    public int getTotalCastedVotes() {
        int v = 0;
        for (MeetingShareholder ms : votableShareholders) {
            v += ms.yesVotesCasted;
            v += ms.noVotesCasted;
        }
        return v;
    }

    public int getTotalCastedNoVotes() {
        return getTotalCastedVotes() - getTotalCastedYesVotes();
    }

    public boolean hasPassed() {
        try {
            if ((float) getTotalCastedYesVotes() / getTotalCastedVotes() >= passRatioOnCastedVotes) {
                if ((float) getTotalCastedYesVotes() / totalVotableShares >= passRatioOnTotalVotes) {
                    return true;
                }
            }
        } catch (ArithmeticException e) {

        }

        return false;
    }

    public boolean hasFailed() {
        try {
            int remainingUncastedVotes = totalVotableShares - getTotalCastedVotes();
            int minimumSharesToPass = Math.round(totalVotableShares * passRatioOnTotalVotes);

            if ((remainingUncastedVotes + getTotalCastedYesVotes()) < minimumSharesToPass) return true;

        } catch (ArithmeticException e) {

        }
        return false;
    }

    public String getAgenda() {
        return "주주총회";
    }

    public void onAgendaPassed(Capitalism plugin) {
        company.setCurrentlyOpenMeeting(null);
        return;
    }

    public MeetingData toData() {
        MeetingData md = new MeetingData();

        md.date = date;
        md.totalVotableShares = totalVotableShares;
        md.passRatioOnCastedVotes = passRatioOnCastedVotes;
        md.passRatioOnTotalVotes = passRatioOnTotalVotes;
        for (MeetingShareholder ms : votableShareholders) {
            md.votableShareholderData.add(ms.toData());
        }

        return md;
    }

    public static Meeting fromData(MeetingData data, Capitalism plugin) {
        ArrayList<MeetingShareholder> shareholders = new ArrayList<MeetingShareholder>();
        for (MeetingShareholderData msd : data.votableShareholderData) {
            shareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return new Meeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                shareholders
        );
    }
}
