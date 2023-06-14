package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.companies.Company;

import java.util.ArrayList;
import java.util.Date;

public class OtherMeeting extends Meeting{
    public OtherMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s,
            String meetingAgenda
    ) {
        super(c, d, tvs, procv, protv, s);
        this.meetingAgenda = meetingAgenda;
    }
    public OtherMeeting() {
        super();
    }

    String meetingAgenda = null;
    @Override
    public String getAgenda() {
        return meetingAgenda;
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.meetingAgenda = meetingAgenda;
        return md;
    }

    public static OtherMeeting fromData(MeetingData data, Capitalism plugin) {
        OtherMeeting om = new OtherMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                new ArrayList<MeetingShareholder>(),
                data.meetingAgenda
        );
        for (MeetingShareholderData msd : data.votableShareholderData) {
            om.votableShareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return om;
    }

}
