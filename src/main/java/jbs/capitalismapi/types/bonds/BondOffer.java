package jbs.capitalismapi.types.bonds;

import jbs.capitalismapi.savetypes.bonds.BondOfferData;
import jbs.capitalismapi.types.entities.EconomicEntity;

public class BondOffer {
    public BondOffer(
            EconomicEntity creditor,
            EconomicEntity debtor,
            double principle,
            float dailyInterestRate,
            int daysToExpiration
    ) {
        this.creditor = creditor;
        this.debtor = debtor;
        this.principle = principle;
        this.dailyInterestRate = dailyInterestRate;
        this.daysToExpiration = daysToExpiration;

    }
    public BondOffer(BondOffer bo) {
        creditor = bo.creditor;
        debtor = bo.debtor;
        principle = bo.principle;
        dailyInterestRate = bo.dailyInterestRate;
        daysToExpiration = bo.daysToExpiration;
    }
    public BondOffer() {
        creditor = null;
        debtor = null;
        principle = 0d;
        dailyInterestRate = 0f;
        daysToExpiration = 1;
    }
    EconomicEntity creditor;
    EconomicEntity debtor;
    double principle;
    float dailyInterestRate;
    int daysToExpiration;

    public void onAccepted() {
    }

    public void onDeclined() {
    }

    public EconomicEntity getCreditor() {
        return creditor;
    }

    public EconomicEntity getDebtor() {
        return debtor;
    }

    public double getPrinciple() {
        return principle;
    }

    public float getDailyInterestRate() {
        return dailyInterestRate;
    }

    public double getTotalInterest() {
        double totalValue = principle;
        for (int i = 0; i < daysToExpiration; i++) {
            totalValue *= (1 + dailyInterestRate);
        }
        return totalValue - principle;
    }

    public double getTotalValue() {
        return principle + getTotalInterest();
    }

    public int getDaysToExpiration() {
        return daysToExpiration;
    }

    public void setCreditor(EconomicEntity creditor) {
        this.creditor = creditor;
    }

    public void setDebtor(EconomicEntity debtor) {
        this.debtor = debtor;
    }

    public void setPrinciple(double principle) {
        this.principle = principle;
    }

    public void setDailyInterestRate(float dailyInterestRate) {
        this.dailyInterestRate = dailyInterestRate;
    }

    public void setDaysToExpiration(int daysToExpiration) {
        this.daysToExpiration = daysToExpiration;
    }

    public BondOfferData toData() {
        BondOfferData lod = new BondOfferData();

        lod.creditorUuid = getCreditor().getUuid().toString();
        lod.debtorUuid = getDebtor().getUuid().toString();
        lod.principle = getPrinciple();
        lod.dailyInterestRate = getDailyInterestRate();
        lod.daysToExpiration = getDaysToExpiration();

        return lod;
    }
}
