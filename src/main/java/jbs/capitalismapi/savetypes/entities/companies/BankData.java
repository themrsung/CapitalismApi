package jbs.capitalismapi.savetypes.entities.companies;

import jbs.capitalismapi.savetypes.banking.BankAccountData;

import java.util.ArrayList;

public class BankData extends CompanyData {
    public BankData(CompanyData cd) {
        super(cd);
        symbol = cd.symbol;
        description = cd.description;
        capital = cd.capital;
        shareCount = cd.shareCount;
        employeeUuids = cd.employeeUuids;
        directorUuids = cd.directorUuids;
        ceoUuid = cd.ceoUuid;
        currentlyOpenMeetingData = cd.currentlyOpenMeetingData;
        currentlyOpenMeetingType = cd.currentlyOpenMeetingType;
        outstandingJobOfferData = cd.outstandingJobOfferData;
        dailyCeoPay = cd.dailyCeoPay;
        dailyDirectorPay = cd.dailyDirectorPay;
        dailyEmployeePay = cd.dailyEmployeePay;
    }
    public BankData() {
        super();
    }

    public ArrayList<BankAccountData> accountData = new ArrayList<BankAccountData>();
    public float withdrawalFeeRate = 0f;
    public float dailyInterestRate = 0f;
}
