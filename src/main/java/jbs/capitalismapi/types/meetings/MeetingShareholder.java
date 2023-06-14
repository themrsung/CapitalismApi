package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.EconomicEntity;

import java.util.UUID;

public class MeetingShareholder {
    public MeetingShareholder(
            EconomicEntity shareholder,
            int totalVotesAvailable,
            int yesVotesCasted,
            int noVotesCasted
    ) {
        this.shareholder = shareholder;
        this.totalVotesAvailable = totalVotesAvailable;
        this.yesVotesCasted = yesVotesCasted;
        this.noVotesCasted = noVotesCasted;
    }
    public MeetingShareholder(
            EconomicEntity shareholder,
            int quantity
    ) {
        this.shareholder = shareholder;
        this.totalVotesAvailable = quantity;
        yesVotesCasted = 0;
        noVotesCasted = 0;
    }
    EconomicEntity shareholder;
    int totalVotesAvailable;
    int yesVotesCasted;
    int noVotesCasted;

    public EconomicEntity getShareholder() {
        return shareholder;
    }

    public void setShareholder(EconomicEntity shareholder) {
        this.shareholder = shareholder;
    }

    public int getTotalVotesAvailable() {
        return totalVotesAvailable;
    }

    public int getYesVotesCasted() {
        return yesVotesCasted;
    }

    public int getNoVotesCasted() {
        return noVotesCasted;
    }

    public void setTotalVotesAvailable(int totalVotesAvailable) {
        this.totalVotesAvailable = totalVotesAvailable;
    }

    public void setYesVotesCasted(int yesVotesCasted) {
        this.yesVotesCasted = yesVotesCasted;
    }
    public void changeYesVotesCasted(int delta) {
        this.yesVotesCasted += delta;
    }

    public void setNoVotesCasted(int noVotesCasted) {
        this.noVotesCasted = noVotesCasted;
    }
    public void changeNoVotesCasted(int delta) {
        this.noVotesCasted += delta;
    }

    public int getRemainingVotes() {
        return totalVotesAvailable - yesVotesCasted;
    }

    public MeetingShareholderData toData() {
        MeetingShareholderData msd = new MeetingShareholderData();

        msd.shareholderUuid = shareholder.getUuid().toString();
        msd.totalVotesAvailable = totalVotesAvailable;
        msd.yesVotesCasted = yesVotesCasted;
        msd.noVotesCasted = noVotesCasted;

        return msd;
    }

    public static MeetingShareholder fromData(MeetingShareholderData data, Capitalism plugin) {
        return new MeetingShareholder(
                plugin.getEntity(UUID.fromString(data.shareholderUuid)),
                data.totalVotesAvailable,
                data.yesVotesCasted,
                data.noVotesCasted
        );
    }
}
