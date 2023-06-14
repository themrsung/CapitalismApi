package jbs.capitalismapi.savetypes.meetings;

public class MeetingShareholderData {
    public MeetingShareholderData(MeetingShareholderData msd) {
        shareholderUuid = msd.shareholderUuid;
        totalVotesAvailable = msd.totalVotesAvailable;
        yesVotesCasted = msd.yesVotesCasted;
        noVotesCasted = msd.noVotesCasted;
    }
    public MeetingShareholderData() {}

    public String shareholderUuid = null;
    public int totalVotesAvailable = 0;
    public int yesVotesCasted = 0;
    public int noVotesCasted = 0;
}
