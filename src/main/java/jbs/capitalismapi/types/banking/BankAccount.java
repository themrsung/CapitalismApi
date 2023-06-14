package jbs.capitalismapi.types.banking;

import jbs.capitalismapi.savetypes.banking.BankAccountData;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.entities.companies.Bank;

import java.util.UUID;

public class BankAccount {
    public BankAccount(
            EconomicEntity owner,
            double balance
    ) {
        this.owner = owner;
        this.balance = balance;
    }
    public BankAccount(BankAccount ba) {
        owner = ba.owner;
        balance = ba.balance;
    }
    public BankAccount() {}
    EconomicEntity owner = null;
    double balance = 0d;

    public EconomicEntity getOwner() {
        return owner;
    }

    public void setOwner(EconomicEntity owner) {
        this.owner = owner;
    }

    Bank bank = null;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getBalance() {
        return balance;
    }
    public double getWithdrawableBalance() {
        double balance = getBalance();
        double fee = balance * bank.getWithdrawalFeeRate();
        return balance - fee;
    }
    public void changeBalance(double delta) { this.balance += delta; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BankAccountData toData() {
        BankAccountData bad = new BankAccountData();

        bad.ownerUuid = owner.getUuid().toString();
        bad.balance = balance;

        return bad;
    }

    public static BankAccount fromData(BankAccountData data, Capitalism plugin) {
        return new BankAccount(
                plugin.getEntity(UUID.fromString(data.ownerUuid)),
                data.balance
        );
    }
}
