package jbs.capitalismapi.types.offers;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.offers.JobOfferData;
import jbs.capitalismapi.savetypes.offers.JobOfferType;
import jbs.capitalismapi.types.entities.companies.Company;
import jbs.capitalismapi.types.entities.player.CapitalismPlayer;

import java.util.UUID;

public class JobOffer {
    public JobOffer(Company from, CapitalismPlayer to) {
        this.from = from;
        this.to = to;
    }
    public JobOffer(JobOffer co) {
        this.from = co.from;
        this.to = co.to;
    }
    public JobOffer() {
        this.from = null;
        this.to = null;
    }


    Company from;
    CapitalismPlayer to;

    public Company getFrom() {
        return from;
    }

    public CapitalismPlayer getTo() {
        return to;
    }

    public void onAccepted() {
        from.removeOutstandingJobOffer(this);
        to.inboundJobOffers.remove(this);
    }

    public void onDeclined() {
        from.removeOutstandingJobOffer(this);
        to.inboundJobOffers.remove(this);
    }

    public JobOfferData toData() {
        JobOfferData jod = new JobOfferData();
        jod.fromSymbol = from.getSymbol();
        jod.toUuid = to.getUuid().toString();
        jod.position = JobOfferType.NONE;

        return jod;
    }

    public static JobOffer fromData(JobOfferData jod, Capitalism plugin) {
        return new JobOffer(
                plugin.getCompany(jod.fromSymbol),
                plugin.getPlayer(UUID.fromString(jod.toUuid))
        );
    }
}
