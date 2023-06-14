package jbs.capitalismapi.types.bonds;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.bonds.BondOfferData;
import jbs.capitalismapi.types.entities.EconomicEntity;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.UUID;

public class BorrowOffer extends BondOffer {
    public BorrowOffer(
            EconomicEntity from,
            EconomicEntity to,
            double principle,
            float dailyInterestRate,
            int daysToExpiration
    ) {
        super(
                to,
                from,
                principle,
                dailyInterestRate,
                daysToExpiration
        );
    }
    public BorrowOffer(BorrowOffer bo) {
        super(bo);
    }
    public BorrowOffer() {
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

        getDebtor().removeOutstandingBorrowOffer(this);
        getCreditor().inboundBorrowOffers.remove(this);
    }

    @Override
    public void onDeclined() {
        super.onDeclined();

        getDebtor().removeOutstandingBorrowOffer(this);
        getCreditor().inboundBorrowOffers.remove(this);
    }

    public static BorrowOffer fromData(BondOfferData data, Capitalism plugin) {
        return new BorrowOffer(
                plugin.getEntity(UUID.fromString(data.creditorUuid)),
                plugin.getEntity(UUID.fromString(data.debtorUuid)),
                data.principle,
                data.dailyInterestRate,
                data.daysToExpiration
        );
    }
}
