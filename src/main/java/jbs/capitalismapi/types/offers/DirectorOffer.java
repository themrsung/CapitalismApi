package jbs.capitalismapi.types.offers;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.offers.JobOfferData;
import jbs.capitalismapi.savetypes.offers.JobOfferType;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;

import java.util.UUID;

public class DirectorOffer extends JobOffer {
    public DirectorOffer(Company from, CapitalismPlayer to) {
        super(from, to);
    }
    public DirectorOffer(DirectorOffer o) {
        super(o);
    }
    public DirectorOffer() {
        super();
    }

    @Override
    public void onAccepted() {
        super.onAccepted();
        from.addDirector(to);
    }

    @Override
    public JobOfferData toData() {
        JobOfferData jod = super.toData();
        jod.position = JobOfferType.DIRECTOR;
        return jod;
    }

    public static DirectorOffer fromData(JobOfferData jod, Capitalism plugin) {
        return new DirectorOffer(
                plugin.getCompany(jod.fromSymbol),
                plugin.getPlayer(UUID.fromString(jod.toUuid))
        );
    }
}
