package jbs.capitalismapi.types.entities.companies;

import jbs.capitalismapi.savetypes.entities.EconomicEntityType;
import jbs.capitalismapi.savetypes.entities.companies.BankData;
import jbs.capitalismapi.types.banking.BankAccount;
import jbs.capitalismapi.types.entities.EconomicEntity;
import jbs.capitalismapi.types.stocks.StockPortfolio;

import java.util.ArrayList;
import java.util.UUID;

public class Bank extends Company {
    public Bank(
            UUID uuid,
            String name,
            double balance,
            StockPortfolio stockPortfolio,
            String symbol,
            int shareCount
    ) {
        super(uuid, name, balance, stockPortfolio, symbol, shareCount);
    }
    public Bank(Bank b) {
        super(b);
        accounts = b.accounts;
        withdrawalFeeRate = b.withdrawalFeeRate;
        dailyInterestRate = b.dailyInterestRate;
    }
    public Bank(UUID uuid) {
        super(uuid);
    }
    public Bank() {
        super();
    }

    ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
    public void setAccounts(ArrayList<BankAccount> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(BankAccount account) {
        for (BankAccount a : accounts) {
            if (a.getOwner().equals(account.getOwner())) {
                a.changeBalance(account.getBalance());
                return;
            }
        }

        accounts.add(account);
    }


    public void deposit(EconomicEntity client, double amount) {
        for (BankAccount a : accounts) {
            if (a.getOwner().equals(client)) {
                a.changeBalance(amount);
                return;
            }
        }

        client.changeBalance(-amount);
        accounts.add(new BankAccount(client, amount));
    }

    public void withdraw(EconomicEntity client, double amount) {
        for (BankAccount a : accounts) {
            if (a.getOwner().equals(client)) {
                double fee = amount * withdrawalFeeRate;

                a.changeBalance(- amount - fee);
                client.changeBalance(amount);
                this.changeBalance(fee);

                return;
            }
        }
    }

    public double getBankBalance(EconomicEntity client) {
        for (BankAccount a : accounts) {
            if (a.getOwner().equals(client)) {
                return a.getBalance();
            }
        }

        return 0d;
    }

    public double getBankWithdrawableBalance(EconomicEntity client) {
        double balance = getBankBalance(client);
        double fee = balance * withdrawalFeeRate;
        return balance - fee;
    }

    @Override
    public double getNetWorth() {
        double e = super.getNetWorth();

        for (BankAccount a : accounts) {
            e -= a.getWithdrawableBalance();
            e += a.getBalance();
        }

        return e;
    }

    float withdrawalFeeRate = 0f;
    float dailyInterestRate = 0f;

    public float getWithdrawalFeeRate() {
        return withdrawalFeeRate;
    }

    public void setWithdrawalFeeRate(float withdrawalFeeRate) {
        this.withdrawalFeeRate = withdrawalFeeRate;
    }

    public float getDailyInterestRate() {
        return dailyInterestRate;
    }

    public void setDailyInterestRate(float dailyInterestRate) {
        this.dailyInterestRate = dailyInterestRate;
    }

    @Override
    public double getAssetValue() {
        double a =  super.getAssetValue();

        for (BankAccount ba : accounts) {
            a += ba.getBalance();
        }

        return a;
    }

    @Override
    public double getLiabilities() {
        double l = super.getLiabilities();

        for (BankAccount ba : accounts) {
            l += ba.getWithdrawableBalance();
        }

        return l;
    }

    @Override
    public BankData toData() {
        BankData bd = new BankData(super.toData());
        bd.type = EconomicEntityType.BANK;

        bd.withdrawalFeeRate = withdrawalFeeRate;
        bd.dailyInterestRate = dailyInterestRate;

        for (BankAccount a : accounts) {
            bd.accountData.add(a.toData());
        }

        return bd;
    }

    public static Bank fromData(BankData data) {
        return new Bank(UUID.fromString(data.uuid));
    }
}
