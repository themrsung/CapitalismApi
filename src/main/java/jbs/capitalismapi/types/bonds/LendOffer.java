package jbs.capitalismapi.types.bonds;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.bonds.BondOfferData;
import jbs.capitalismapi.types.entities.EconomicEntity;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.UUID;

public class LendOffer extends BondOffer {
    public LendOffer(
            EconomicEntity from,
            EconomicEntity to,
            double principle,
            float dailyInterestRate,
            int daysToExpiration
    ) {
        super(
                from,
                to,
                principle,
                dailyInterestRate,
                daysToExpiration
        );
    }
    public LendOffer(LendOffer lo) {
        super(lo);
    }
    public LendOffer() {
        super();
    }

    @Override
    public void onAccepted() {
        super.onAccepted();

        Bond b = new Bond(
                getDebtor(),
                getCreditor(),
                getTotalValue(),
                DateUtils.addDays(new Date(), getDaysToExpiration())
        );

        getCreditor().changeBalance(-getPrinciple());
        getDebtor().changeBalance(getPrinciple());

        getCreditor().addBond(b);
        getDebtor().outstandingDebt.add(b);

        getCreditor().removeOutstandingLendOffer(this);
        getDebtor().inboundLendOffers.remove(this);
    }

    @Override
    public void onDeclined() {
        super.onDeclined();

        getCreditor().removeOutstandingLendOffer(this);
        getDebtor().inboundLendOffers.remove(this);
    }

    public static LendOffer fromData(BondOfferData data, Capitalism plugin) {
        return new LendOffer(
                plugin.getEntity(UUID.fromString(data.creditorUuid)),
                plugin.getEntity(UUID.fromString(data.debtorUuid)),
                data.principle,
                data.dailyInterestRate,
                data.daysToExpiration
        );
    }
}
