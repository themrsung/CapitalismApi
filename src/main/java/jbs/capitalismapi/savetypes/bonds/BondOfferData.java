package jbs.capitalismapi.savetypes.bonds;

public class BondOfferData {
    public BondOfferData(BondOfferData bod) {
        creditorUuid = bod.creditorUuid;
        debtorUuid = bod.debtorUuid;
        principle = bod.principle;
        dailyInterestRate = bod.dailyInterestRate;
        daysToExpiration = bod.daysToExpiration;
    }
    public BondOfferData() {}
    public String creditorUuid = null;
    public String debtorUuid = null;
    public double principle = 0d;
    public float dailyInterestRate = 0f;
    public int daysToExpiration = 1;
}
