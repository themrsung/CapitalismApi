package jbs.capitalismapi.types.bonds;

import jbs.capitalismapi.Capitalism;
import jbs.capitalismapi.savetypes.bonds.BondData;
import jbs.capitalismapi.types.entities.EconomicEntity;

import java.util.Date;
import java.util.UUID;

public class Bond {
    public Bond(
            EconomicEntity debtor,
            EconomicEntity creditor,
            double amount,
            Date expiration
    ) {
        this.uuid = UUID.randomUUID();
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
        this.expiration = expiration;
    }
    public Bond(Bond b) {
        this.uuid = b.uuid;
        this.debtor = b.debtor;
        this.creditor = b.creditor;
        this.amount = b.amount;
        this.expiration = b.expiration;
    }
    public Bond() {
        this.uuid = null;
        this.debtor = null;
        this.creditor = null;
        this.amount = 0d;
        this.expiration = null;
    }
    UUID uuid;
    EconomicEntity debtor;
    EconomicEntity creditor;
    double amount;
    Date expiration;

    public void onExpiration() {
        if (debtor.getBalance() < amount) {
            onDefaulted();
            return;
        }

        repay();
    }

    public void repay() {
        debtor.changeBalance(-amount);
        creditor.changeBalance(amount);

        debtor.outstandingDebt.remove(this);
        creditor.removeBond(this);
    }

    public void onForgiven() {

        debtor.outstandingDebt.remove(this);
        creditor.removeBond(this);
    }

    public void onDefaulted() {
        double payable = debtor.getBalance();
        double payment = Math.min(payable, amount);

        debtor.changeBalance(-payment);
        creditor.changeBalance(payment);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public EconomicEntity getDebtor() {
        return debtor;
    }

    public void setDebtor(EconomicEntity debtor) {
        this.debtor = debtor;
    }

    public EconomicEntity getCreditor() {
        return creditor;
    }

    public void setCreditor(EconomicEntity creditor) {
        this.creditor = creditor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public BondData toData() {
        BondData bd = new BondData();

        bd.debtorUuid = debtor.getUuid().toString();
        bd.creditorUuid = creditor.getUuid().toString();
        bd.amount = amount;
        bd.expiration = expiration;
        bd.uuid = uuid.toString();

        return bd;
    }

    public static Bond fromData(BondData data, Capitalism plugin) {
        Bond b = new Bond();

        b.setUuid(UUID.fromString(data.uuid));
        b.setDebtor(plugin.getEntity(UUID.fromString(data.debtorUuid)));
        b.setCreditor(plugin.getEntity(UUID.fromString(data.creditorUuid)));
        b.setAmount(data.amount);
        b.setExpiration(data.expiration);

        return b;
    }
}
