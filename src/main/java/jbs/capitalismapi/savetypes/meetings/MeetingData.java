package jbs.capitalismapi.savetypes.meetings;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Date;

public class MeetingData {
    public MeetingData(MeetingData md) {
        date = md.date;
        totalVotableShares = md.totalVotableShares;
        passRatioOnCastedVotes = md.passRatioOnCastedVotes;
        passRatioOnTotalVotes = md.passRatioOnTotalVotes;
        votableShareholderData = md.votableShareholderData;

        dividendPerShare = md.dividendPerShare;
        newName = md.newName;
        newCeoUuid = md.newCeoUuid;
        meetingAgenda = md.meetingAgenda;
        numberOfNewSharesToIssue = md.numberOfNewSharesToIssue;
        numberOfStocksToRetire = md.numberOfStocksToRetire;
        numberOfNewSharesPerExistingShare = md.numberOfNewSharesPerExistingShare;
    }
    public MeetingData() {}
    public Date date = null;
    public int totalVotableShares = 1;
    public float passRatioOnCastedVotes = 0f;
    public float passRatioOnTotalVotes = 0f;
    public ArrayList<MeetingShareholderData> votableShareholderData = new ArrayList<MeetingShareholderData>();

    public double dividendPerShare = 0d;
    @Nullable
    public String newName = null;
    public String newCeoUuid = null;
    public String meetingAgenda = null;
    public int numberOfNewSharesToIssue = 0;
    public int numberOfStocksToRetire = 0;
    public int numberOfNewSharesPerExistingShare = 0;
}
