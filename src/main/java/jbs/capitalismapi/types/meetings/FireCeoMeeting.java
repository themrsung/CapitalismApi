package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.companies.Company;

import java.util.ArrayList;
import java.util.Date;

public class FireCeoMeeting extends Meeting{
    public FireCeoMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s
    ) {
        super(c, d, tvs, procv, protv, s);
    }
    public FireCeoMeeting() {
        super();
    }

    @Override
    public String getAgenda() {
        return "대표이사 " + company.getCeo().getName() + " 해임의 건";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        company.setCeo(null);
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        return md;
    }

    public static FireCeoMeeting fromData(MeetingData data, Capitalism plugin) {
        ArrayList<MeetingShareholder> shareholders = new ArrayList<MeetingShareholder>();
        for (MeetingShareholderData msd : data.votableShareholderData) {
            shareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return new FireCeoMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                shareholders
        );
    }
}
