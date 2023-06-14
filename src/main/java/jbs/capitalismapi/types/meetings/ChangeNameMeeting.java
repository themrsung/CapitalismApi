package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.companies.Company;

import java.util.ArrayList;
import java.util.Date;

public class ChangeNameMeeting extends Meeting{
    public ChangeNameMeeting(
            Company c,
            Date d,
            int tvs,
            float procv,
            float protv,
            ArrayList<MeetingShareholder> s,
            String newName
    ) {
        super(c, d, tvs, procv, protv, s);
        this.newName = newName;
    }
    public ChangeNameMeeting() {
        super();
    }

    String newName = null;

    public String getNewName() {
        return newName;
    }

    @Override
    public String getAgenda() {
        return "사명 변경의 건 (" + company.getName() + " -> " + newName + ")";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        company.setName(newName);
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.newName = newName;
        return md;
    }

    public static ChangeNameMeeting fromData(MeetingData data, Capitalism plugin) {
        ArrayList<MeetingShareholder> shareholders = new ArrayList<MeetingShareholder>();
        for (MeetingShareholderData msd : data.votableShareholderData) {
            shareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return new ChangeNameMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                shareholders,
                data.newName
        );
    }

}
