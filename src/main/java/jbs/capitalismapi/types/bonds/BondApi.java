package jbs.capitalismapi.types.bonds;

import jbs.capitalismapi.savetypes.bonds.BondApiData;
import jbs.capitalismapi.types.entities.EconomicEntityApi;
import jbs.capitalismapi.CapitalismApi;

import java.util.Date;
import java.util.UUID;

public class BondApi {
    public BondApi(
            UUID uuid,
            EconomicEntityApi debtor,
            EconomicEntityApi creditor,
            double amount,
            Date expiration
    ) {
        this.uuid = uuid;
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
        this.expiration = expiration;
    }
    public BondApi() {}

    // UUID of this bond
    // 채권의 UUID
    UUID uuid = null;

    // Debtor of this bond
    // 채무자
    EconomicEntityApi debtor = null;

    // Creditor of this bond
    // 채권자
    EconomicEntityApi creditor = null;

    // Total amount due on expiration
    // 만기일에 지급할 원리금 총액
    double amount;

    // Expiry
    // 만기일
    Date expiration;


    public UUID getUuid() {
        return uuid;
    }

    public EconomicEntityApi getDebtor() {
        return debtor;
    }

    public EconomicEntityApi getCreditor() {
        return creditor;
    }

    public double getAmount() {
        return amount;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDebtor(EconomicEntityApi debtor) {
        this.debtor = debtor;
    }

    public void setCreditor(EconomicEntityApi creditor) {
        this.creditor = creditor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public BondApiData toData() {
        BondApiData data = new BondApiData();

        data.uuid = uuid.toString();
        data.debtorUuid = debtor.getUuid().toString();
        data.creditorUuid = creditor.getUuid().toString();
        data.amount = amount;
        data.expiration = expiration;

        return data;
    }

    public static BondApi fromData(BondApiData data, CapitalismApi plugin) {
        return new BondApi(
                UUID.fromString(data.uuid),
                plugin.getEntity(UUID.fromString(data.debtorUuid)),
                plugin.getEntity(UUID.fromString(data.creditorUuid)),
                data.amount,
                data.expiration
        );
    }
}
