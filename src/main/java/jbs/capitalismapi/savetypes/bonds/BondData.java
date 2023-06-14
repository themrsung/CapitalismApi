package jbs.capitalismapi.savetypes.bonds;

import java.util.Date;

public class BondData {
    public BondData(BondData bd) {
        debtorUuid = bd.debtorUuid;
        creditorUuid = bd.creditorUuid;
        amount = bd.amount;
        expiration = bd.expiration;
    }
    public BondData() {}
    public String uuid = null;
    public String debtorUuid = null;
    public String creditorUuid = null;
    public double amount = 0d;
    public Date expiration = null;
}
