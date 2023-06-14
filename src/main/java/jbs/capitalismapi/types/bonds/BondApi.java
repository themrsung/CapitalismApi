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
    UUID uuid = null;
    EconomicEntityApi debtor = null;
    EconomicEntityApi creditor = null;
    double amount;
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

    public BondApiData toData() {
        BondApiData bad = new BondApiData();

        bad.uuid = uuid.toString();
        bad.debtorUuid = debtor.getUuid().toString();
        bad.creditorUuid = creditor.getUuid().toString();
        bad.amount = amount;
        bad.expiration = expiration;

        return bad;
    }

    public static BondApi fromData(BondApiData data, CapitalismApi plugin) {
        return new BondApi(
                UUID.fromString(data.uuid),
                plugin.getEntity(UUID.fromString(data.debtorUuid)),
                plugin.getEntity(UUID.fromString(data.creditorUuid)),
                amount,
                expiration
        );
    }
}
