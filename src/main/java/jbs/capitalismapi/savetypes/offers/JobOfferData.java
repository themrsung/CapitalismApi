package jbs.capitalismapi.savetypes.offers;

public class JobOfferData {
    public JobOfferData(JobOfferData jod) {
        this.fromSymbol = jod.fromSymbol;
        this.toUuid = jod.toUuid;
        this.position = jod.position;
    }
    public JobOfferData() {}
    public String fromSymbol = null;
    public String toUuid = null;
    public JobOfferType position = null;
}
