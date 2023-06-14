package jbs.capitalismapi.types.meetings;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.meetings.MeetingData;
import jbs.capitalismapi.savetypes.meetings.MeetingShareholderData;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;
import jbs.capitalismapi.types.offers.CeoOffer;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class HireCeoMeeting extends Meeting {
    public HireCeoMeeting(
            Company company,
            Date date,
            int totalVotableShares,
            float passRatioOnCastedVotes,
            float passRatioOnTotalVotes,
            ArrayList<MeetingShareholder> votableShareholders,
            CapitalismPlayer newCeo
    ) {
        super(company, date, totalVotableShares, passRatioOnCastedVotes, passRatioOnTotalVotes, votableShareholders);
        this.newCeo = newCeo;

    }
    public HireCeoMeeting () {
        super();
    }

    CapitalismPlayer newCeo;

    public CapitalismPlayer getNewCeo() {
        return newCeo;
    }

    @Override
    public String getAgenda() {
        return "대표이사 " + newCeo.getName() + " 선임의 건";
    }

    @Override
    public void onAgendaPassed(Capitalism plugin) {
        super.onAgendaPassed(plugin);

        company.sendJobOffer(new CeoOffer(company, newCeo));
    }

    @Override
    public MeetingData toData() {
        MeetingData md = super.toData();
        md.newCeoUuid = newCeo.getUuid().toString();
        return md;
    }

    public static HireCeoMeeting fromData(MeetingData data, Capitalism plugin) {
        HireCeoMeeting hcm = new HireCeoMeeting(
                null,
                data.date,
                data.totalVotableShares,
                data.passRatioOnCastedVotes,
                data.passRatioOnTotalVotes,
                new ArrayList<MeetingShareholder>(),
                plugin.getPlayer(UUID.fromString(data.newCeoUuid))
        );
        for (MeetingShareholderData msd : data.votableShareholderData) {
            hcm.votableShareholders.add(MeetingShareholder.fromData(msd, plugin));
        }

        return hcm;
    }
}
