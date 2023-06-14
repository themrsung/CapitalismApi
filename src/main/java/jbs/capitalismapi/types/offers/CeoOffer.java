package jbs.capitalismapi.types.offers;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.offers.JobOfferData;
import jbs.capitalismapi.savetypes.offers.JobOfferType;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;

import java.util.UUID;

public class CeoOffer extends JobOffer {
    public CeoOffer(Company from, CapitalismPlayer to) {
        super(from, to);
    }
    public CeoOffer(CeoOffer co) {
        super(co);
    }
    public CeoOffer() {
        super();
    }

    @Override
    public void onAccepted() {
        super.onAccepted();
        from.setCeo(to);
    }

    @Override
    public JobOfferData toData() {
        JobOfferData jod = super.toData();
        jod.position = JobOfferType.CEO;
        return jod;
    }

    public static CeoOffer fromData(JobOfferData jod, Capitalism plugin) {
        return new CeoOffer(
                (Company) plugin.getCompany(jod.fromSymbol),
                plugin.getPlayer(UUID.fromString(jod.toUuid))
        );
    }
}
